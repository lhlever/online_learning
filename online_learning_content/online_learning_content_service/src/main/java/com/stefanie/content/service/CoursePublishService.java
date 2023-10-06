package com.stefanie.content.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stefanie.online_learning_content_model.dto.CoursePreviewDto;
import com.stefanie.online_learning_content_model.po.CoursePublish;

import java.io.File;

/**
* @author stefanie
* @description 针对表【course_publish(课程发布)】的数据库操作Service
* @createDate 2023-09-17 21:44:20
*/
public interface CoursePublishService extends IService<CoursePublish> {

    /**
    * @Description:  获取课程预览信息
    * @Param: [courseId]
    * @Author: stefanie
    * @Date: 2023/10/4~15:06
    */
    public CoursePreviewDto getCoursePreviewDto(Long courseId);

    /**
    * @Description: 提交审核
    * @Param: [companyId, courseId]
    * @Author: stefanie
    * @Date: 2023/10/4~19:43
    */
    public void commitAudit(Long companyId,Long courseId);

    /**
    * @Description:  课程发布
    * @Param: [companyId, courseId]
    * @Author: stefanie
    * @Date: 2023/10/4~21:35
    */
    public void publish(Long companyId,Long courseId);

    /**
    * @Description: 课程页面静态化
    * @Param: [courseId]
    * @Author: stefanie
    * @Date: 2023/10/5~12:59
    */
    public File generateCourseHtml(Long courseId);

    /**
    * @Description:  上传静态化页面到minio
    * @Param: [courseId, file]
    * @Author: stefanie
    * @Date: 2023/10/5~13:01
    */
    public void uploadCourseHtml(Long courseId,File file);
}
