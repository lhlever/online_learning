package com.stefanie.content;

import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.online_learning_content_model.dto.QueryCourseParamDto;
import com.stefanie.online_learning_content_model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class CourseBaseInfoController {
    @ApiOperation("按条件查询分页查询课程")
    @PostMapping("course/list")
    public PageResult<CourseBase> list(PageParam pageParam,@RequestBody QueryCourseParamDto queryCourseParamDto){

        return null;
    }
}
