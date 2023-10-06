package com.stefanie.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.online_learning_content_model.po.CourseTeacher;
import com.stefanie.content.service.CourseTeacherService;
import com.stefanie.content.courseMapper.CourseTeacherMapper;
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




