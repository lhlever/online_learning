package com.stefanie.online_learning_content_model.po;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 课程分类
* @TableName course_category
*/
@Data
@TableName("course_category")
public class CourseCategory implements Serializable {

    /**
    * 主键
    */
    @NotBlank(message="[主键]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("主键")
    @Length(max= 20,message="编码长度不能超过20")
    private String id;
    /**
    * 分类名称
    */
    @NotBlank(message="[分类名称]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("分类名称")
    @Length(max= 32,message="编码长度不能超过32")
    private String name;
    /**
    * 分类标签默认和名称一样
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("分类标签默认和名称一样")
    @Length(max= 32,message="编码长度不能超过32")
    private String label;
    /**
    * 父结点id（第一级的父节点是0，自关联字段id）
    */
    @NotBlank(message="[父结点id（第一级的父节点是0，自关联字段id）]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("父结点id（第一级的父节点是0，自关联字段id）")
    @Length(max= 20,message="编码长度不能超过20")
    private String parentid;
    /**
    * 是否显示
    */
    @ApiModelProperty("是否显示")
    private Integer isShow;
    /**
    * 排序字段
    */
    @ApiModelProperty("排序字段")
    private Integer orderby;
    /**
    * 是否叶子
    */
    @ApiModelProperty("是否叶子")
    private Integer isLeaf;

    /**
    * 主键
    */
    private void setId(String id){
    this.id = id;
    }

    /**
    * 分类名称
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 分类标签默认和名称一样
    */
    private void setLabel(String label){
    this.label = label;
    }

    /**
    * 父结点id（第一级的父节点是0，自关联字段id）
    */
    private void setParentid(String parentid){
    this.parentid = parentid;
    }

    /**
    * 是否显示
    */
    private void setIsShow(Integer isShow){
    this.isShow = isShow;
    }

    /**
    * 排序字段
    */
    private void setOrderby(Integer orderby){
    this.orderby = orderby;
    }

    /**
    * 是否叶子
    */
    private void setIsLeaf(Integer isLeaf){
    this.isLeaf = isLeaf;
    }


    /**
    * 主键
    */
    private String getId(){
    return this.id;
    }

    /**
    * 分类名称
    */
    private String getName(){
    return this.name;
    }

    /**
    * 分类标签默认和名称一样
    */
    private String getLabel(){
    return this.label;
    }

    /**
    * 父结点id（第一级的父节点是0，自关联字段id）
    */
    private String getParentid(){
    return this.parentid;
    }

    /**
    * 是否显示
    */
    private Integer getIsShow(){
    return this.isShow;
    }

    /**
    * 排序字段
    */
    private Integer getOrderby(){
    return this.orderby;
    }

    /**
    * 是否叶子
    */
    private Integer getIsLeaf(){
    return this.isLeaf;
    }

}
