package com.stefanie.content;

import com.stefanie.content.service.CoursePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author：stefanie
 * @Package：com.stefanie.content
 * @Project：online_learning
 * @name：CoursePublishController
 * @Date：2023/10/4 11:01
 * @Filename：CoursePublishController
 * @Description：
 */
@Controller
public class CoursePublishController {
    @Autowired
    private CoursePublishService coursePublishService;
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable Long courseId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model",coursePublishService.getCoursePreviewDto(courseId));
        modelAndView.setViewName("course_template");
        return modelAndView;
    }

    @PostMapping("/courseaudit/commit/{courseId}")
    public void commitAudit(@PathVariable Long courseId){
        Long companyId = 1232141425L;
        coursePublishService.commitAudit(companyId,courseId);
    }

    @PostMapping("/coursepublish/{courseId}")
    public void publish(@PathVariable Long courseId){
        Long companyId = 1232141425L;
        coursePublishService.publish(companyId,courseId);
    }
}
