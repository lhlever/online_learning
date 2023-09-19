package com.stefanie.online_learning_content_service.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.online_learning_content_model.dto.AddCourseDto;
import com.stefanie.online_learning_content_model.dto.CourseBaseInfoDto;
import com.stefanie.online_learning_content_model.dto.QueryCourseParamDto;
import com.stefanie.online_learning_content_model.po.CourseBase;

/**
* @author stefanie
* @description 针对表【course_base(课程基本信息)】的数据库操作Service
* @createDate 2023-09-17 21:44:20
*/
public interface CourseBaseService extends IService<CourseBase> {
    public PageResult<CourseBase> getPageByCondition(PageParam pageParam, QueryCourseParamDto queryCourseParamDto);

    /**
     * @Description: 新增课程
     * @Param: [companyId 机构id, addCourseDto 课程基本信息]
     * @Author: stefanie
     * @Date: 2023/9/19~12:27
     */
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);
}
