package com.xuecheng.media.service;

import com.xuecheng.media.model.po.MediaProcess;

import java.util.List;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.media.service
 * @Project：online_learning
 * @name：MediaFileProcessService
 * @Date：2023/10/2 19:30
 * @Filename：MediaFileProcessService
 * @Description：
 */
public interface MediaFileProcessService {
    /**
    * @Description:  执行器获取自己的任务
    * @Param: [shareIndex, shareTotal, count]
    * @Author: stefanie
    * @Date: 2023/10/2~20:12
    */
    public List<MediaProcess> getMediaProcessList(int shareIndex,int shareTotal,int count);

    /**
    * @Description:  分布式锁
    * @Param: [id]
    * @Author: stefanie
    * @Date: 2023/10/2~20:12
    */
    public int startTask(Long id);

    /**
    * @Description:  保存任务结果
    * @Param: [taskId, status, fileId, url, errorMsg]
    * @Author: stefanie
    * @Date: 2023/10/2~20:12
    */
    void saveProcessFinishStatus(Long taskId,String status,String fileId,String url,String errorMsg);
}
