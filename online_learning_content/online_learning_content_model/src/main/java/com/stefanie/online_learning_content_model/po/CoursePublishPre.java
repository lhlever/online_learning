package com.stefanie.online_learning_content_model.po;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 课程发布
* @TableName course_publish_pre
*/
@Data
@TableName("course_publish_pre")
public class CoursePublishPre implements Serializable {

    /**
    * 主键
    */
    @NotNull(message="[主键]不能为空")
    @ApiModelProperty("主键")
    private Long id;
    /**
    * 机构ID
    */
    @NotNull(message="[机构ID]不能为空")
    @ApiModelProperty("机构ID")
    private Long companyId;
    /**
    * 公司名称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("公司名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String companyName;
    /**
    * 课程名称
    */
    @NotBlank(message="[课程名称]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("课程名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String name;
    /**
    * 适用人群
    */
    @NotBlank(message="[适用人群]不能为空")
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("适用人群")
    @Length(max= 500,message="编码长度不能超过500")
    private String users;
    /**
    * 标签
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("标签")
    @Length(max= 32,message="编码长度不能超过32")
    private String tags;
    /**
    * 创建人
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("创建人")
    @Length(max= 32,message="编码长度不能超过32")
    private String username;
    /**
    * 大分类
    */
    @NotBlank(message="[大分类]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("大分类")
    @Length(max= 20,message="编码长度不能超过20")
    private String mt;
    /**
    * 大分类名称
    */
    @NotBlank(message="[大分类名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("大分类名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String mtName;
    /**
    * 小分类
    */
    @NotBlank(message="[小分类]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("小分类")
    @Length(max= 20,message="编码长度不能超过20")
    private String st;
    /**
    * 小分类名称
    */
    @NotBlank(message="[小分类名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("小分类名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String stName;
    /**
    * 课程等级
    */
    @NotBlank(message="[课程等级]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("课程等级")
    @Length(max= 32,message="编码长度不能超过32")
    private String grade;
    /**
    * 教育模式
    */
    @NotBlank(message="[教育模式]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("教育模式")
    @Length(max= 32,message="编码长度不能超过32")
    private String teachmode;
    /**
    * 课程图片
    */
    @NotBlank(message="[课程图片]不能为空")
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("课程图片")
    @Length(max= 500,message="编码长度不能超过500")
    private String pic;
    /**
    * 课程介绍
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("课程介绍")
    @Length(max= -1,message="编码长度不能超过-1")
    private String description;
    /**
    * 课程营销信息，json格式
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("课程营销信息，json格式")
    @Length(max= -1,message="编码长度不能超过-1")
    private String market;
    /**
    * 所有课程计划，json格式
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("所有课程计划，json格式")
    @Length(max= -1,message="编码长度不能超过-1")
    private String teachplan;
    /**
    * 教师信息，json格式
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("教师信息，json格式")
    @Length(max= -1,message="编码长度不能超过-1")
    private String teachers;
    /**
    * 提交时间
    */
    @ApiModelProperty("提交时间")
    private LocalDateTime createDate;
    /**
    * 审核时间
    */
    @ApiModelProperty("审核时间")
    private LocalDateTime auditDate;
    /**
    * 状态
    */
    @Size(max= 10,message="编码长度不能超过10")
    @ApiModelProperty("状态")
    @Length(max= 10,message="编码长度不能超过10")
    private String status;
    /**
    * 备注
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("备注")
    @Length(max= 500,message="编码长度不能超过500")
    private String remark;
    /**
    * 收费规则，对应数据字典--203
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("收费规则，对应数据字典--203")
    @Length(max= 32,message="编码长度不能超过32")
    private String charge;
    /**
    * 现价
    */
    @ApiModelProperty("现价")
    private Double price;
    /**
    * 原价
    */
    @ApiModelProperty("原价")
    private Double originalPrice;
    /**
    * 课程有效期天数
    */
    @ApiModelProperty("课程有效期天数")
    private Integer validDays;



}
