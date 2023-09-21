package com.stefanie.online_learning_content_model.dto;

import com.stefanie.online_learning_content_model.po.Teachplan;
import com.stefanie.online_learning_content_model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_model.dto
 * @Project：online_learning
 * @name：TeachPlanDto
 * @Date：2023/9/20 19:09
 * @Filename：TeachPlanDto
 * @Description：课程计划信息模型类
 */
@Data
@ToString(callSuper = true)
public class TeachPlanDto extends Teachplan {
    //与媒资关联的信息
    private TeachplanMedia teachplanMedia;
    //定义小章节
    private List<TeachPlanDto> teachPlanTreeNodes;

}
