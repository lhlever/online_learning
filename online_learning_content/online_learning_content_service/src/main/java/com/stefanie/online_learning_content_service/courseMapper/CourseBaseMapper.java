package com.stefanie.online_learning_content_service.courseMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stefanie.online_learning_content_model.po.CourseBase;
import org.apache.ibatis.annotations.Mapper;

/**
* @author stefanie
* @description 针对表【course_base(课程基本信息)】的数据库操作Mapper
* @createDate 2023-09-17 21:44:20
* @Entity generator.domain.CourseBase
*/
@Mapper
public interface CourseBaseMapper extends BaseMapper<CourseBase> {

}




