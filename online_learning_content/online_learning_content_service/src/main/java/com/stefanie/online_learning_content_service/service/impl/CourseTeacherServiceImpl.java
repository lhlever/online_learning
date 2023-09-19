package com.stefanie.online_learning_content_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.online_learning_content_model.po.CourseTeacher;
import com.stefanie.online_learning_content_service.service.CourseTeacherService;
import com.stefanie.online_learning_content_service.courseMapper.CourseTeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author stefanie
* @description 针对表【course_teacher(课程-教师关系表)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:21
*/
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher>
    implements CourseTeacherService{

}




