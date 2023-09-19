package com.stefanie.online_learning_content_model.po;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 课程基本信息
* @TableName course_base
*/
@TableName("course_base")
@Data
public class CourseBase implements Serializable {

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
    * 机构名称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("机构名称")
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
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("适用人群")
    @Length(max= 500,message="编码长度不能超过500")
    private String users;
    /**
    * 课程标签
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("课程标签")
    @Length(max= 50,message="编码长度不能超过50")
    private String tags;
    /**
    * 大分类
    */
    @NotBlank(message="[大分类]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("大分类")
    @Length(max= 20,message="编码长度不能超过20")
    private String mt;
    /**
    * 小分类
    */
    @NotBlank(message="[小分类]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("小分类")
    @Length(max= 20,message="编码长度不能超过20")
    private String st;
    /**
    * 课程等级
    */
    @NotBlank(message="[课程等级]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("课程等级")
    @Length(max= 32,message="编码长度不能超过32")
    private String grade;
    /**
    * 教育模式(common普通，record 录播，live直播等）
    */
    @NotBlank(message="[教育模式(common普通，record 录播，live直播等）]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("教育模式(common普通，record 录播，live直播等）")
    @Length(max= 32,message="编码长度不能超过32")
    private String teachmode;
    /**
    * 课程介绍
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("课程介绍")
    @Length(max= -1,message="编码长度不能超过-1")
    private String description;
    /**
    * 课程图片
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("课程图片")
    @Length(max= 500,message="编码长度不能超过500")
    private String pic;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;
    /**
    * 修改时间
    */
    @ApiModelProperty("修改时间")
    private LocalDateTime changeDate;
    /**
    * 创建人
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("创建人")
    @Length(max= 50,message="编码长度不能超过50")
    private String createPeople;
    /**
    * 更新人
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("更新人")
    @Length(max= 50,message="编码长度不能超过50")
    private String changePeople;
    /**
    * 审核状态
    */
    @NotBlank(message="[审核状态]不能为空")
    @Size(max= 10,message="编码长度不能超过10")
    @ApiModelProperty("审核状态")
    @Length(max= 10,message="编码长度不能超过10")
    private String auditStatus;
    /**
    * 课程发布状态 未发布  已发布 下线
    */
    @NotBlank(message="[课程发布状态 未发布  已发布 下线]不能为空")
    @Size(max= 10,message="编码长度不能超过10")
    @ApiModelProperty("课程发布状态 未发布  已发布 下线")
    @Length(max= 10,message="编码长度不能超过10")
    private String status;



}
