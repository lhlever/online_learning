package com.stefanie.online_learning_content_service.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stefanie.online_learning_content_model.dto.CourseCategoryDto;
import com.stefanie.online_learning_content_model.po.CourseCategory;

import java.util.List;

/**
* @author stefanie
* @description 针对表【course_category(课程分类)】的数据库操作Service
* @createDate 2023-09-17 21:44:21
*/
public interface CourseCategoryService extends IService<CourseCategory> {
    public List<CourseCategoryDto> getTreeCourse();
}
