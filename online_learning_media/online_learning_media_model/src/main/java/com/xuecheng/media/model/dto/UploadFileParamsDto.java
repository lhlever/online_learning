package com.xuecheng.media.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.media.model.dto
 * @Project：online_learning
 * @name：UploadFileParamsDto
 * @Date：2023/10/1 16:11
 * @Filename：UploadFileParamsDto
 * @Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true )
public class UploadFileParamsDto {
    private String filename;
    private String fileType;
    private Long fileSize;
    private String tags;
    private String username;
    private String remark;
}
