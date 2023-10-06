package com.stefanie.content.service.jobHandler;

import com.stefanie.content.courseMapper.CoursePublishMapper;
import com.stefanie.content.feignClient.CourseIndex;
import com.stefanie.content.feignClient.SearchServiceClient;
import com.stefanie.exception.GlobalException;
import com.stefanie.content.service.CoursePublishService;
import com.stefanie.online_learning_content_model.dto.CoursePreviewDto;
import com.stefanie.online_learning_content_model.po.CoursePublish;
import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.messagesdk.service.MessageProcessAbstract;
import com.xuecheng.messagesdk.service.MqMessageService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_service.service.jobHandler
 * @Project：online_learning
 * @name：CoursePublishTask
 * @Date：2023/10/5 9:57
 * @Filename：CoursePublishTask
 * @Description：课程发布任务类
 */
@Component
@Slf4j
public class CoursePublishTask extends MessageProcessAbstract {

    @Autowired
    CoursePublishService coursePublishService;

    @Autowired
    SearchServiceClient searchServiceClient;

    @Autowired
    CoursePublishMapper coursePublishMapper;

    @XxlJob("CoursePublishJobHandler")
    public void coursePublishJobHandler() throws Exception{
        //分片参数
        int sharedIndex= XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        process(sharedIndex,shardTotal,"course_publish",30,60);
    }


    /**
    * @Description:  执行发布任务的逻辑
    * @Param: [mqMessage]
    * @Author: stefanie
    * @Date: 2023/10/5~9:59
    */
    @Override
    public boolean execute(MqMessage mqMessage) {
        //0.从mqMessage中拿到课程id
        Long courseId = Long.valueOf(mqMessage.getBusinessKey1());
        //1.课程发布页面静态化写入minio
        generateCourseHtml(mqMessage,courseId);
        //2.向elasticsearch写索引数据
        saveCourseIndex(mqMessage,courseId);
        //3.向redis写缓存


        return false;
    }

    /**
    * @Description:  课程发布页面静态化写入minio
    * @Param: [mqMessage, courseId]
    * @Author: stefanie
    * @Date: 2023/10/5~10:04
    */
    private void generateCourseHtml(MqMessage mqMessage,Long courseId){
        Long taskId = mqMessage.getId();
        MqMessageService mqMessageService = this.getMqMessageService();
        //保证任务幂等性
        int stageOne = mqMessageService.getStageOne(taskId);
        if (stageOne>0){
            log.debug("课程发布页面静态化已完成,无需执行");
        }
        File file = coursePublishService.generateCourseHtml(courseId);
        if (file==null){
            GlobalException.cast("生成的静态页面为空");
            return ;
        }
        coursePublishService.uploadCourseHtml(courseId,file);
        mqMessageService.completedStageOne(taskId);
    }

    /**
    * @Description: 向elasticsearch写索引数据
    * @Param: [mqMessage, courseId]
    * @Author: stefanie
    * @Date: 2023/10/5~10:13
    */
    private void saveCourseIndex(MqMessage mqMessage,Long courseId){
        Long taskId = mqMessage.getId();
        MqMessageService mqMessageService = this.getMqMessageService();
        //保证任务幂等性
        int stageTwo = mqMessageService.getStageTwo(taskId);
        if (stageTwo>0){
            log.debug("向elasticsearch写索引数据已完成,无需执行");
        }
        CoursePublish coursePublish = coursePublishMapper.selectById(courseId);
        CourseIndex courseIndex = new CourseIndex();
        BeanUtils.copyProperties(coursePublish,courseIndex);
        Boolean add = searchServiceClient.add(courseIndex);
        if (!add){
            GlobalException.cast("远程调用搜索服务添加课程索引失败");
        }
        mqMessageService.completedStageTwo(taskId);
    }
}
