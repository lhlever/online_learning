package com.stefanie.online_learning_content_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.online_learning_content_model.po.CoursePublish;
import com.stefanie.online_learning_content_service.service.CoursePublishService;
import com.stefanie.online_learning_content_service.courseMapper.CoursePublishMapper;
import org.springframework.stereotype.Service;

/**
* @author stefanie
* @description 针对表【course_publish(课程发布)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:20
*/
@Service
public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish>
    implements CoursePublishService{

}




