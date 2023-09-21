package com.stefanie.online_learning_content_model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_model.dto
 * @Project：online_learning
 * @name：EditCourseDto
 * @Date：2023/9/20 16:52
 * @Filename：EditCourseDto
 * @Description：
 */
@Data
@ToString(callSuper = true)
public class EditCourseDto extends AddCourseDto implements Serializable {
    @ApiModelProperty(value = "课程id",required = true)
    private Long id;
}
