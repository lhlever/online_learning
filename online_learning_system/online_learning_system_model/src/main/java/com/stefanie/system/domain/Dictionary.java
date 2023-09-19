package com.stefanie.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 数据字典
 * @TableName dictionary
 */
@TableName(value ="dictionary")
@Data
public class Dictionary implements Serializable {
    /**
     * id标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据字典名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 数据字典代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 数据字典项--json格式

     */
    @TableField(value = "item_values")
    private String itemValues;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
