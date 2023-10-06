package com.stefanie.content.utils;

import com.stefanie.online_learning_content_model.dto.CourseCategoryDto;
import com.stefanie.online_learning_content_model.po.CourseCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_service.utils
 * @Project：online_learning
 * @name：Utils
 * @Date：2023/9/18 21:02
 * @Filename：Utils
 * @Description：
 */
public class Utils {
    public static void tracking(List<CourseCategory> list, CourseCategoryDto root){
        for(CourseCategory courseCategory:list){
            if (courseCategory.getParentid().equals(root.getId())){
                CourseCategoryDto courseCategoryDto=new CourseCategoryDto();
                courseCategoryDto.setId(courseCategory.getId());
                courseCategoryDto.setName(courseCategory.getName());
                courseCategoryDto.setLabel(courseCategory.getLabel());
                courseCategoryDto.setOrderby(courseCategory.getOrderby());
                courseCategoryDto.setParentid(courseCategory.getParentid());
                courseCategoryDto.setIsLeaf(courseCategory.getIsLeaf());
                courseCategoryDto.setIsShow(courseCategory.getIsShow());
                if (courseCategory.getIsLeaf()!=1){
                    List<CourseCategoryDto> courseCategoryDtoList=new ArrayList<>();
                    courseCategoryDto.setChildrenTreeNodes(courseCategoryDtoList);
                }

                System.out.println(root.getChildrenTreeNodes());
                root.getChildrenTreeNodes().add(courseCategoryDto);
                Utils.tracking(list,courseCategoryDto);
            }
        }
    }

    public static void tracking4TeachPlan(List<CourseCategory> list, CourseCategoryDto root){
        for(CourseCategory courseCategory:list){
            if (courseCategory.getParentid().equals(root.getId())){
                CourseCategoryDto courseCategoryDto=new CourseCategoryDto();
                courseCategoryDto.setId(courseCategory.getId());
                courseCategoryDto.setName(courseCategory.getName());
                courseCategoryDto.setLabel(courseCategory.getLabel());
                courseCategoryDto.setOrderby(courseCategory.getOrderby());
                courseCategoryDto.setParentid(courseCategory.getParentid());
                courseCategoryDto.setIsLeaf(courseCategory.getIsLeaf());
                courseCategoryDto.setIsShow(courseCategory.getIsShow());
                if (courseCategory.getIsLeaf()!=1){
                    List<CourseCategoryDto> courseCategoryDtoList=new ArrayList<>();
                    courseCategoryDto.setChildrenTreeNodes(courseCategoryDtoList);
                }

                System.out.println(root.getChildrenTreeNodes());
                root.getChildrenTreeNodes().add(courseCategoryDto);
                Utils.tracking(list,courseCategoryDto);
            }
        }
    }
}
