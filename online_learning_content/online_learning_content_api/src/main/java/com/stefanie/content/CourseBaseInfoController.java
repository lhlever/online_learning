package com.stefanie.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.online_learning_content_model.dto.AddCourseDto;
import com.stefanie.online_learning_content_model.dto.CourseBaseInfoDto;
import com.stefanie.online_learning_content_model.dto.QueryCourseParamDto;
import com.stefanie.online_learning_content_model.po.CourseBase;
import com.stefanie.online_learning_content_service.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public CourseBaseInfoDto createCourseBase(@RequestBody AddCourseDto addCourseDto){
        return courseBaseService.createCourseBase(1232141425L,addCourseDto);
    }
}
