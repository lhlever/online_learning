package com.stefanie.online_learning_content_model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_model.dto
 * @Project：online_learning
 * @name：BindTeachPlanMediaDto
 * @Date：2023/10/4 8:39
 * @Filename：BindTeachPlanMediaDto
 * @Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindTeachPlanMediaDto {
    private String mediaId;
    private String fileName;
    private Long teachplanId;
}
