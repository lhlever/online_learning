package com.stefanie.content;

import com.stefanie.online_learning_content_model.dto.CoursePreviewDto;
import com.stefanie.online_learning_content_service.service.CourseBaseService;
import com.stefanie.online_learning_content_service.service.CoursePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：stefanie
 * @Package：com.stefanie.content
 * @Project：online_learning
 * @name：OpenController
 * @Date：2023/10/4 15:56
 * @Filename：OpenController
 * @Description：
 */
@RestController
@RequestMapping(("/open"))
public class OpenController {
    @Autowired
    private CourseBaseService courseBaseService;
    @Autowired
    private CoursePublishService coursePublishService;
    /**
    * @Description: 根据课程id获取课程信息
    * @Param: [courseId]
    * @Author: stefanie
    * @Date: 2023/10/4~16:01
    */
    @GetMapping("/course/whole/{courseId}")
    public CoursePreviewDto getPrevirewInfo(@PathVariable("courseId") Long courseId){
        CoursePreviewDto coursePreviewInfo=coursePublishService.getCoursePreviewDto(courseId);
        return coursePreviewInfo;
    }
}
