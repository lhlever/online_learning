package com.stefanie.online_learning_content_model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_model.domain
 * @Project：online_learning
 * @name：QueryCourseParamDto
 * @Date：2023/9/17 15:33
 * @Filename：QueryCourseParamDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCourseParamDto {
    private String auditStatus;
    private String courseName;
    private String publishStatus;
}
