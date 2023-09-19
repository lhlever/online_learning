package com.stefanie.online_learning_content_service.courseMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stefanie.online_learning_content_model.po.CourseCategory;
import org.apache.ibatis.annotations.Mapper;

/**
* @author stefanie
* @description 针对表【course_category(课程分类)】的数据库操作Mapper
* @createDate 2023-09-17 21:44:21
* @Entity generator.domain.CourseCategory
*/
@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

}




