package com.stefanie.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.online_learning_content_model.po.CoursePublishPre;
import com.stefanie.content.service.CoursePublishPreService;
import com.stefanie.content.courseMapper.CoursePublishPreMapper;
import org.springframework.stereotype.Service;

/**
* @author stefanie
* @description 针对表【course_publish_pre(课程发布)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:20
*/
@Service
public class CoursePublishPreServiceImpl extends ServiceImpl<CoursePublishPreMapper, CoursePublishPre>
    implements CoursePublishPreService{

}




