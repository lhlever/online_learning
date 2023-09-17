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
* 课程发布
* @TableName course_publish
*/
@Data
@TableName("course_publish")
public class CoursePublish implements Serializable {

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
    * 发布时间
    */
    @ApiModelProperty("发布时间")
    private Date createDate;
    /**
    * 上架时间
    */
    @ApiModelProperty("上架时间")
    private Date onlineDate;
    /**
    * 下架时间
    */
    @ApiModelProperty("下架时间")
    private Date offlineDate;
    /**
    * 发布状态
    */
    @Size(max= 10,message="编码长度不能超过10")
    @ApiModelProperty("发布状态")
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

    /**
    * 主键
    */
    private void setId(Long id){
    this.id = id;
    }

    /**
    * 机构ID
    */
    private void setCompanyId(Long companyId){
    this.companyId = companyId;
    }

    /**
    * 公司名称
    */
    private void setCompanyName(String companyName){
    this.companyName = companyName;
    }

    /**
    * 课程名称
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 适用人群
    */
    private void setUsers(String users){
    this.users = users;
    }

    /**
    * 标签
    */
    private void setTags(String tags){
    this.tags = tags;
    }

    /**
    * 创建人
    */
    private void setUsername(String username){
    this.username = username;
    }

    /**
    * 大分类
    */
    private void setMt(String mt){
    this.mt = mt;
    }

    /**
    * 大分类名称
    */
    private void setMtName(String mtName){
    this.mtName = mtName;
    }

    /**
    * 小分类
    */
    private void setSt(String st){
    this.st = st;
    }

    /**
    * 小分类名称
    */
    private void setStName(String stName){
    this.stName = stName;
    }

    /**
    * 课程等级
    */
    private void setGrade(String grade){
    this.grade = grade;
    }

    /**
    * 教育模式
    */
    private void setTeachmode(String teachmode){
    this.teachmode = teachmode;
    }

    /**
    * 课程图片
    */
    private void setPic(String pic){
    this.pic = pic;
    }

    /**
    * 课程介绍
    */
    private void setDescription(String description){
    this.description = description;
    }

    /**
    * 课程营销信息，json格式
    */
    private void setMarket(String market){
    this.market = market;
    }

    /**
    * 所有课程计划，json格式
    */
    private void setTeachplan(String teachplan){
    this.teachplan = teachplan;
    }

    /**
    * 教师信息，json格式
    */
    private void setTeachers(String teachers){
    this.teachers = teachers;
    }

    /**
    * 发布时间
    */
    private void setCreateDate(Date createDate){
    this.createDate = createDate;
    }

    /**
    * 上架时间
    */
    private void setOnlineDate(Date onlineDate){
    this.onlineDate = onlineDate;
    }

    /**
    * 下架时间
    */
    private void setOfflineDate(Date offlineDate){
    this.offlineDate = offlineDate;
    }

    /**
    * 发布状态
    */
    private void setStatus(String status){
    this.status = status;
    }

    /**
    * 备注
    */
    private void setRemark(String remark){
    this.remark = remark;
    }

    /**
    * 收费规则，对应数据字典--203
    */
    private void setCharge(String charge){
    this.charge = charge;
    }

    /**
    * 现价
    */
    private void setPrice(Double price){
    this.price = price;
    }

    /**
    * 原价
    */
    private void setOriginalPrice(Double originalPrice){
    this.originalPrice = originalPrice;
    }

    /**
    * 课程有效期天数
    */
    private void setValidDays(Integer validDays){
    this.validDays = validDays;
    }


    /**
    * 主键
    */
    private Long getId(){
    return this.id;
    }

    /**
    * 机构ID
    */
    private Long getCompanyId(){
    return this.companyId;
    }

    /**
    * 公司名称
    */
    private String getCompanyName(){
    return this.companyName;
    }

    /**
    * 课程名称
    */
    private String getName(){
    return this.name;
    }

    /**
    * 适用人群
    */
    private String getUsers(){
    return this.users;
    }

    /**
    * 标签
    */
    private String getTags(){
    return this.tags;
    }

    /**
    * 创建人
    */
    private String getUsername(){
    return this.username;
    }

    /**
    * 大分类
    */
    private String getMt(){
    return this.mt;
    }

    /**
    * 大分类名称
    */
    private String getMtName(){
    return this.mtName;
    }

    /**
    * 小分类
    */
    private String getSt(){
    return this.st;
    }

    /**
    * 小分类名称
    */
    private String getStName(){
    return this.stName;
    }

    /**
    * 课程等级
    */
    private String getGrade(){
    return this.grade;
    }

    /**
    * 教育模式
    */
    private String getTeachmode(){
    return this.teachmode;
    }

    /**
    * 课程图片
    */
    private String getPic(){
    return this.pic;
    }

    /**
    * 课程介绍
    */
    private String getDescription(){
    return this.description;
    }

    /**
    * 课程营销信息，json格式
    */
    private String getMarket(){
    return this.market;
    }

    /**
    * 所有课程计划，json格式
    */
    private String getTeachplan(){
    return this.teachplan;
    }

    /**
    * 教师信息，json格式
    */
    private String getTeachers(){
    return this.teachers;
    }

    /**
    * 发布时间
    */
    private Date getCreateDate(){
    return this.createDate;
    }

    /**
    * 上架时间
    */
    private Date getOnlineDate(){
    return this.onlineDate;
    }

    /**
    * 下架时间
    */
    private Date getOfflineDate(){
    return this.offlineDate;
    }

    /**
    * 发布状态
    */
    private String getStatus(){
    return this.status;
    }

    /**
    * 备注
    */
    private String getRemark(){
    return this.remark;
    }

    /**
    * 收费规则，对应数据字典--203
    */
    private String getCharge(){
    return this.charge;
    }

    /**
    * 现价
    */
    private Double getPrice(){
    return this.price;
    }

    /**
    * 原价
    */
    private Double getOriginalPrice(){
    return this.originalPrice;
    }

    /**
    * 课程有效期天数
    */
    private Integer getValidDays(){
    return this.validDays;
    }

}
