package com.stefanie.online_learning_content_model.po;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
*
* @TableName teachplan_media
*/
@TableName("teacherplan_media")
@Data
public class TeachplanMedia implements Serializable {

    /**
    * 主键
    */
    @NotNull(message="[主键]不能为空")
    @ApiModelProperty("主键")
    private Long id;
    /**
    * 媒资文件id
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("媒资文件id")
    @Length(max= 32,message="编码长度不能超过32")
    private String mediaId;
    /**
    * 课程计划标识
    */
    @NotNull(message="[课程计划标识]不能为空")
    @ApiModelProperty("课程计划标识")
    private Long teachplanId;
    /**
    * 课程标识
    */
    @NotNull(message="[课程标识]不能为空")
    @ApiModelProperty("课程标识")
    private Long courseId;
    /**
    * 媒资文件原始名称
    */
    @NotBlank(message="[媒资文件原始名称]不能为空")
    @Size(max= 150,message="编码长度不能超过150")
    @ApiModelProperty("媒资文件原始名称")
    @Length(max= 150,message="编码长度不能超过150")
    private String mediaFilename;
    /**
    *
    */
    @ApiModelProperty("")
    private Date createDate;
    /**
    * 创建人
    */
    @Size(max= 60,message="编码长度不能超过60")
    @ApiModelProperty("创建人")
    @Length(max= 60,message="编码长度不能超过60")
    private String createPeople;
    /**
    * 修改人
    */
    @Size(max= 60,message="编码长度不能超过60")
    @ApiModelProperty("修改人")
    @Length(max= 60,message="编码长度不能超过60")
    private String changePeople;



}
