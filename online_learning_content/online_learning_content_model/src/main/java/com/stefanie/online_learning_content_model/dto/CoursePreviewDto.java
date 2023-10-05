package com.stefanie.online_learning_content_model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_model.dto
 * @Project：online_learning
 * @name：CoursePreviewDto
 * @Date：2023/10/4 14:59
 * @Filename：CoursePreviewDto
 * @Description：课程预览模型类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePreviewDto {
    //课程基本信息&课程营销信息
    private CourseBaseInfoDto courseBase;

    //课程计划信息
    private List<TeachPlanDto> teachPlans;
    //课程师资信息
    // TODO: 2023/10/4
}
