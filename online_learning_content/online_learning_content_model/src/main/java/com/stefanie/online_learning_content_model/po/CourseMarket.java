package com.stefanie.online_learning_content_model.po;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 课程营销信息
* @TableName course_market
*/
@Data
@TableName("course_market")
public class CourseMarket implements Serializable {

    /**
    * 主键，课程id
    */
    @NotNull(message="[主键，课程id]不能为空")
    @ApiModelProperty("主键，课程id")
    private Long id;
    /**
    * 收费规则，对应数据字典
    */
    @NotBlank(message="[收费规则，对应数据字典]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("收费规则，对应数据字典")
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
    * 咨询qq
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("咨询qq")
    @Length(max= 32,message="编码长度不能超过32")
    private String qq;
    /**
    * 微信
    */
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("微信")
    @Length(max= 64,message="编码长度不能超过64")
    private String wechat;
    /**
    * 电话
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("电话")
    @Length(max= 32,message="编码长度不能超过32")
    private String phone;
    /**
    * 有效期天数
    */
    @ApiModelProperty("有效期天数")
    private Integer validDays;


}
