package com.stefanie.content;

import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.online_learning_content_model.dto.CourseCategoryDto;
import com.stefanie.online_learning_content_model.dto.QueryCourseParamDto;
import com.stefanie.online_learning_content_model.po.CourseBase;
import com.stefanie.online_learning_content_service.service.CourseBaseService;
import com.stefanie.online_learning_content_service.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Api(value = "课程分类树形接口",tags = "课程分类接口")
@CrossOrigin
public class CourseCategoryController {
    @Autowired
    private CourseBaseService courseBaseService;
    @Autowired
    private CourseCategoryService courseCategoryService;
    @ApiOperation("查询课程树形分类")
    @GetMapping("course-category/tree-nodes")
    public List<CourseCategoryDto> list(){
        return courseCategoryService.getTreeCourse();
    }
}
