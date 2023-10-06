package com.stefanie.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.online_learning_content_model.dto.CourseCategoryDto;
import com.stefanie.online_learning_content_model.po.CourseCategory;
import com.stefanie.content.service.CourseCategoryService;
import com.stefanie.content.courseMapper.CourseCategoryMapper;
import com.stefanie.content.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author stefanie
* @description 针对表【course_category(课程分类)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:21
*/
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory>
    implements CourseCategoryService{
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;
    public List<CourseCategoryDto> getTreeCourse(){
        List<CourseCategory> courseCategories = courseCategoryMapper.selectList(null);
        courseCategories.forEach(i-> System.out.println(i));
        CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
        courseCategoryDto.setId("1");
        List<CourseCategoryDto> courseCategoryDtoList=new ArrayList<>();
        courseCategoryDto.setChildrenTreeNodes(courseCategoryDtoList);
        Utils.tracking(courseCategories,courseCategoryDto);
        System.out.println(courseCategoryDto);
        return courseCategoryDto.getChildrenTreeNodes();
    }
}




