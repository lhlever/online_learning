package com.stefanie.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：stefanie
 * @Package：com.stefanie.domain
 * @Project：online_learning
 * @name：PageParam
 * @Date：2023/9/17 15:23
 * @Filename：PageParam
 * @Description: 分页查询参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {
    //当前页码
    @ApiModelProperty("当前展示页码")
    private Long pageNo=1L;
    //每页记录数
    @ApiModelProperty("每页记录数")
    private Long pageSize=100L;
}
