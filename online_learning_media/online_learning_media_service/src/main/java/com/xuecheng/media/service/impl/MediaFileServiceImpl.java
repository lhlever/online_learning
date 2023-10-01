package com.xuecheng.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.exception.GlobalException;
import com.xuecheng.media.mapper.MediaFilesMapper;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import javax.management.ObjectName;
import javax.swing.plaf.ViewportUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/9/10 8:58
 */
@Service
@Slf4j
public class MediaFileServiceImpl implements MediaFileService {

    @Autowired
    MediaFilesMapper mediaFilesMapper;

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket.files}")
    private String bucket_mediafiles;

    @Value("${minio.bucket.videofiles}")
    private String bucket_video;

    @Autowired
    private MediaFileService currentProxy;

    @Override
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParam pageParams, QueryMediaParamsDto queryMediaParamsDto) {

        //构建查询条件对象
        LambdaQueryWrapper<MediaFiles> queryWrapper = new LambdaQueryWrapper<>();

        //分页对象
        Page<MediaFiles> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<MediaFiles> pageResult = mediaFilesMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<MediaFiles> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<MediaFiles> mediaListResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return mediaListResult;

    }

    /**
     * @Description: 根据扩展名获取mimeType
     * @Param: [extension]
     * @Author: stefanie
     * @Date: 2023/10/1~16:25
     */
    public String getMimeType(String extension) {
        if (extension == null) {
            extension = "";
        }
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extension);
        String applicationOctetStreamValue = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (extensionMatch != null) {
            applicationOctetStreamValue = extensionMatch.getMimeType();
        }
        return applicationOctetStreamValue;
    }

    /**
     * @Description: 上传文件到minio
     * @Param: [localFilePath, mimeType, bucket, objectName]
     * @Author: stefanie
     * @Date: 2023/10/1~16:32
     */
    public boolean addMediaFilesToMinio(String localFilePath, String mimeType, String bucket, String objectName) {
        try {
            UploadObjectArgs build = UploadObjectArgs.builder()
                    .bucket(bucket)
                    .filename(localFilePath)
                    .object(objectName)
                    .contentType(mimeType)
                    .build();
            minioClient.uploadObject(build);
            log.debug("上传文件到minio成功,bucket:{},objectName:{}", bucket, objectName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件出错,bucket:{},objectName:{},错误信息:{}", bucket, objectName, e.getCause());
        }
        return false;
    }

    /**
    * @Description:获取文件默认存储路径：年/月/日
    * @Param: []
    * @Author: stefanie
    * @Date: 2023/10/1~16:40
    */
    public String getDefaultFloaderPath(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String folder = simpleDateFormat.format(new Date()).replace("-", "/")+"/";
        return folder;
    }

    /**
    * @Description:  获取文件的md5
    * @Param: [file]
    * @Author: stefanie
    * @Date: 2023/10/1~16:44
    */
    private String getFileMd5(File file){
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            String md5 = DigestUtils.md5Hex(fileInputStream);
            return md5;
        }catch (Exception e){
            e.printStackTrace();
            log.error("md5出错");
            return null;
        }
    }

    @Transactional
    public MediaFiles addMediaFilesToDb(Long companyId,String filemd5,UploadFileParamsDto uploadFileParamsDto,String bucket,String objectName){
        MediaFiles mediaFiles = mediaFilesMapper.selectById(filemd5);
        if (mediaFiles==null){
            mediaFiles=new MediaFiles();
            BeanUtils.copyProperties(uploadFileParamsDto,mediaFiles);
            mediaFiles.setId(filemd5);
            mediaFiles.setCompanyId(companyId);
            mediaFiles.setBucket(bucket);
            mediaFiles.setFilePath(objectName);
            mediaFiles.setFileId(filemd5);
            mediaFiles.setUrl("/"+bucket+"/"+objectName);
            mediaFiles.setCreateDate(LocalDateTime.now());
            mediaFiles.setStatus("1");
            mediaFiles.setAuditStatus("002003");
            int insert = mediaFilesMapper.insert(mediaFiles);
            if (insert<=0){
                log.error("向数据库写入失败,bucket:{},objectName:{}", bucket, objectName);
                return null;
            }
            return mediaFiles;
        }
        log.debug("minio文件系统已存在该文件");
        return mediaFiles;
    }

    @Override
    public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath) {
        //1.将文件上传到minio
        //1.1根据文件扩展名获取mimeType
        String filename = uploadFileParamsDto.getFilename();
        String mimeType = getMimeType(filename.substring(filename.lastIndexOf(".")));
        //1.2上传文件的参数信息
        String defaultFloaderPath = getDefaultFloaderPath();
        String fileMd5 = getFileMd5(new File(localFilePath));
        String objectName=defaultFloaderPath + fileMd5;
        boolean b = addMediaFilesToMinio(localFilePath, mimeType, bucket_mediafiles,objectName);
        if (!b){
            GlobalException.cast("上传文件失败");
        }
        //2.将文件信息写入到数据库中
        MediaFiles mediaFiles = currentProxy.addMediaFilesToDb(companyId, fileMd5, uploadFileParamsDto, bucket_mediafiles, objectName);
        if (mediaFiles==null){
            GlobalException.cast("文件保存失败");
        }
        UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
        BeanUtils.copyProperties(mediaFiles,uploadFileResultDto);
        return uploadFileResultDto;
    }

}
