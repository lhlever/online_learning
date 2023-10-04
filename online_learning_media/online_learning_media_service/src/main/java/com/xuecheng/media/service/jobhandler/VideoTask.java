package com.xuecheng.media.service.jobhandler;

import com.stefanie.utils.Mp4VideoUtil;
import com.xuecheng.media.mapper.MediaFilesMapper;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaFileProcessService;
import com.xuecheng.media.service.MediaFileService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
* @Description:
* @Param: 分布式视频任务调度处理
* @Author: stefanie
* @Date: 2023/10/2~20:30
*/

@Component
@Slf4j
public class VideoTask {

    @Autowired
    MediaFileProcessService mediaFileProcessService;

    @Autowired
    MediaFileService mediaFileService;

    @Value("${videoprocess.ffmpegpath}")
    private String ffmpeg_path;

    /**
     * 分片广播任务
     */
    @XxlJob("videoJobHandler")
    public void shardingJobHandler() throws Exception {

        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        //1.1 确定cpu核心数
        int processors = Runtime.getRuntime().availableProcessors();
        //1.2 查询待处理任务
        List<MediaProcess> mediaProcessList = mediaFileProcessService.getMediaProcessList(shardIndex, shardTotal, processors);
        //1.3 任务数量
        int size = mediaProcessList.size();
        log.debug("取到任务个数为：{}",size);
        //1.4 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        //1.x 使用计数器
        CountDownLatch countDownLatch = new CountDownLatch(size);
        mediaProcessList.forEach(mediaProcess -> {
            //1.5 将任务加入线程池
            executorService.execute(()->{
                try {
                    //1.1 分布式锁获取任务
                    int i = mediaFileProcessService.startTask(mediaProcess.getId());
                    //2.执行视频转码
                    if (i <= 0) {
                        log.error("抢占任务失败，任务id:{}", mediaProcess.getId());
                        return;
                    }
                    //2.1下载源avi视频到本地
                    File file = mediaFileService.downloadFileFromMinio(mediaProcess.getBucket(), mediaProcess.getFilePath());
                    if (file == null) {
                        log.error("下载视频到本地出错，任务id:{}，bucket:{},objectName", mediaProcess.getId(), mediaProcess.getBucket(), mediaProcess.getFilePath());
                        //保存任务失败的结果
                        mediaFileProcessService.saveProcessFinishStatus(mediaProcess.getId(), "3", mediaProcess.getFileId(), null, "下载视频到本地出错");
                        return;
                    }
                    String video_path = file.getAbsolutePath();
                    //2.2转换后mp4文件的名称
                    String mp4_name = mediaProcess.getFileId() + ".mp4";
                    //2.3转换后mp4文件的路径
                    File minio = null;
                    try {
                        minio = File.createTempFile("minio", ".mp4");
                    } catch (IOException e) {
                        log.error("创建临时文件异常，{}", e.getMessage());
                        mediaFileProcessService.saveProcessFinishStatus(mediaProcess.getId(), "3", mediaProcess.getFileId(), null, "创建临时文件异常");
                        return;
                    }
                    String mp4_path = minio.getAbsolutePath();
                    //2.4创建工具类对象
                    Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpeg_path, video_path, mp4_name, mp4_path);
                    //2.5开始视频转换，成功将返回success
                    String result = videoUtil.generateMp4();
                    if (!"success".equals(result)) {
                        log.error("视频转码失败，原因：{}", result);
                        mediaFileProcessService.saveProcessFinishStatus(mediaProcess.getId(), "3", mediaProcess.getFileId(), null, "视频转码失败");
                    }
                    //3.转码后的视频上传到minio
                    String objectName = getFilePath(mediaProcess.getFileId(), ".mp4");
                    boolean b = mediaFileService.addMediaFilesToMinio(mp4_path, "video/mp4", mediaProcess.getBucket(), objectName);
                    if (!b) {
                        log.error("上传转码后视频到minio失败");
                        mediaFileProcessService.saveProcessFinishStatus(mediaProcess.getId(), "3", mediaProcess.getFileId(), null, "上传转码后视频到minio失败");
                        return;
                    }
                    //保存任务处理结果
                    mediaFileProcessService.saveProcessFinishStatus(mediaProcess.getId(), "2", mediaProcess.getFileId(), mediaProcess.getBucket() + "/" + objectName, "");
                }finally {
                    //不论执行成功还是异常，计数器都减一
                    countDownLatch.countDown();
                }
            });

        });
        //阻塞,指定最大限度等待时间，阻塞最多一定时间就接触阻塞
        countDownLatch.await(30,TimeUnit.MINUTES);

    }

    private String getFilePath(String fileMd5,String fileExt) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + fileExt;
    }
}
