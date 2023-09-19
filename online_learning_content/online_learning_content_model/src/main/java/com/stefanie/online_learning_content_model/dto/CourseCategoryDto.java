package com.stefanie.online_learning_content_model.dto;

import com.stefanie.online_learning_content_model.po.CourseCategory;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_model.dto
 * @Project：online_learning
 * @name：CourseCategoryDto
 * @Date：2023/9/18 19:42
 * @Filename：CourseCategoryDto
 * @Description：课程树形结构
 */
@Data
@ToString(callSuper = true)
public class CourseCategoryDto extends CourseCategory implements Serializable {
    //子节点
    List<CourseCategoryDto> childrenTreeNodes;
}
