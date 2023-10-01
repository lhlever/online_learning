package com.xuecheng.media.model.dto;

import com.xuecheng.media.model.po.MediaFiles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.media.model.dto
 * @Project：online_learning
 * @name：UploadFileResultDto
 * @Date：2023/10/1 16:08
 * @Filename：UploadFileResultDto
 * @Description：
 */
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class UploadFileResultDto extends MediaFiles {
}
