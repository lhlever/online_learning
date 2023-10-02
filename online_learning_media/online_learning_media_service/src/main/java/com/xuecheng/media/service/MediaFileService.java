package com.xuecheng.media.service;

import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.domain.RestResponse;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description 媒资文件管理业务类
 * @author Mr.M
 * @date 2022/9/10 8:55
 * @version 1.0
 */
public interface MediaFileService {

 /**
  * @description 媒资文件查询方法
  * @param pageParams 分页参数
  * @param queryMediaParamsDto 查询条件
  * @return com.xuecheng.base.model.PageResult<com.xuecheng.media.model.po.MediaFiles>
  * @author Mr.M
  * @date 2022/9/10 8:57
 */
 public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParam pageParams, QueryMediaParamsDto queryMediaParamsDto);
 /**
 * @Description:
 * @Param: [companyId机构id, uploadFileParamsDto文件信息, localFilePath文件本地路径]
 * @Author: stefanie
 * @Date: 2023/10/1~16:17
 */
 public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath);
 public MediaFiles addMediaFilesToDb(Long companyId,String filemd5,UploadFileParamsDto uploadFileParamsDto,String bucket,String objectName);

 /**
 * @Description:  检查文件是否存在
 * @Param: [fileMd5]
 * @Author: stefanie
 * @Date: 2023/10/1~21:29
 */
 public RestResponse<Boolean> checkFile(String fileMd5);
 /**
 * @Description:  检查分块是否存在
 * @Param: [fileMd5, chunkIndex]
 * @Author: stefanie
 * @Date: 2023/10/1~21:29
 */
 public RestResponse<Boolean> checkChunk(String fileMd5,int chunkIndex);

 /**
 * @Description:  上传分块
 * @Param: [fileMd5, chunk, localChunkFilePath]
 * @Author: stefanie
 * @Date: 2023/10/2~11:29
 */
 public RestResponse uploadChunk(String fileMd5,int chunk,String localChunkFilePath);

 /**
 * @Description:  合并分块
 * @Param: [companyId, fileMd5, chunkTotal, uploadFileParamsDto]
 * @Author: stefanie
 * @Date: 2023/10/2~11:34
 */
 public RestResponse mergeChunks(Long companyId,String fileMd5,int chunkTotal,UploadFileParamsDto uploadFileParamsDto);
}
