package com.stefanie.online_learning_content_model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_model.dto
 * @Project：online_learning
 * @name：SaveTeachplanDto
 * @Date：2023/9/20 21:23
 * @Filename：SaveTeachplanDto
 * @Description：
 */
@Data
@ToString
public class SaveTeachplanDto {

    /***
     * 教学计划id
     */
    private Long id;

    /**
     * 课程计划名称
     */
    private String pname;

    /**
     * 课程计划父级Id
     */
    private Long parentid;

    /**
     * 层级，分为1、2、3级
     */
    private Integer grade;

    /**
     * 课程类型:1视频、2文档
     */
    private String mediaType;


    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 课程发布标识
     */
    private Long coursePubId;


    /**
     * 是否支持试学或预览（试看）
     */
    private String isPreview;



}
