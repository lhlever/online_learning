package com.stefanie.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.exception.ValidationGroups;
import com.stefanie.online_learning_content_model.dto.AddCourseDto;
import com.stefanie.online_learning_content_model.dto.CourseBaseInfoDto;
import com.stefanie.online_learning_content_model.dto.EditCourseDto;
import com.stefanie.online_learning_content_model.dto.QueryCourseParamDto;
import com.stefanie.online_learning_content_model.po.CourseBase;
import com.stefanie.online_learning_content_service.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_api
 * @Project：online_learning
 * @name：CourseBaseInfoController
 * @Date：2023/9/17 16:05
 * @Filename：CourseBaseInfoController
 */
@RestController
//@CrossOrigin
@Api(value = "课程信息管理接口",tags = "课程管理接口")
@CrossOrigin
public class CourseBaseInfoController {
    @Autowired
    private CourseBaseService courseBaseService;
    @ApiOperation("按条件查询分页查询课程")
    @PostMapping("course/list")
    public PageResult<CourseBase> list(PageParam pageParam,@RequestBody QueryCourseParamDto queryCourseParamDto){
        return courseBaseService.getPageByCondition(pageParam,queryCourseParamDto);
    }

    @ApiOperation("新增课程")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated(ValidationGroups.Insert.class) AddCourseDto addCourseDto){
        return courseBaseService.createCourseBase(1232141425L,addCourseDto);
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseById(@PathVariable Long courseId){
        return courseBaseService.getCourseBaseInfoDto(courseId);
    }

    @ApiOperation("根据id修改课程")
    @PutMapping("/course")
    public CourseBaseInfoDto updateCourseById(@RequestBody @Validated(ValidationGroups.Update.class) EditCourseDto editCourseDto){
        return courseBaseService.updateCourseBaseInfoDto(1232141425L, editCourseDto);
    }
}
