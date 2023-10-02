package com.xuecheng.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.domain.RestResponse;
import com.stefanie.exception.GlobalException;
import com.xuecheng.media.mapper.MediaFilesMapper;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
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
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public RestResponse<Boolean> checkFile(String fileMd5) {
        //1.先查询数据库
        MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
        if (mediaFiles!=null){
            //2.如果数据库存在在查询minio
            GetObjectArgs build = GetObjectArgs.builder().bucket(mediaFiles.getBucket())
                    .object(mediaFiles.getFilePath()).build();
            try {
                GetObjectResponse object = minioClient.getObject(build);
                if (object!=null){
                    //文件已存在
                    return RestResponse.success(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return RestResponse.success(false);
    }

    /**
    * @Description:  得到分块文件的目录
    * @Param: [fileMd5]
    * @Author: stefanie
    * @Date: 2023/10/1~21:41
    */
    private String getChunkFileFolderPath(String fileMd5) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + "chunk" + "/";
    }
    /**
    * @Description:  得到合并后文件路径
    * @Param: [fileMd5]
    * @Author: stefanie
    * @Date: 2023/10/2~11:53
    */
    private String getFilePath(String fileMd5,String fileExt) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + fileExt;
    }
    @Override
    public RestResponse<Boolean> checkChunk(String fileMd5, int chunkIndex) {
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);

        GetObjectArgs build = GetObjectArgs.builder().bucket(bucket_video)
                .object(chunkFileFolderPath+chunkIndex).build();
        try {
            GetObjectResponse object = minioClient.getObject(build);
            if (object!=null){
                //文件已存在
                return RestResponse.success(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResponse.success(false);
    }

    /**
    * @Description:  丛minio下载文件
    * @Param: [bucket, objectName]
    * @Author: stefanie
    * @Date: 2023/10/2~12:01
    */
    public File downloadFileFromMinio(String bucket,String objectName){
        File minioFile=null;
        FileOutputStream fileOutputStream=null;
        try {
            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build()
            );
            minioFile=File.createTempFile("minio",".merge");
            fileOutputStream=new FileOutputStream(minioFile);
            IOUtils.copy(inputStream,fileOutputStream);
            return minioFile;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
    * @Description:  上传分块
    * @Param: [fileMd5, chunk, localChunkFilePath]
    * @Author: stefanie
    * @Date: 2023/10/2~12:17
    */
    @Override
    public RestResponse uploadChunk(String fileMd5, int chunk, String localChunkFilePath) {
        String chunkFilePath = getChunkFileFolderPath(fileMd5)+chunk;
        boolean b = addMediaFilesToMinio(localChunkFilePath, getMimeType(null), bucket_video, chunkFilePath);
        if (!b){
            return RestResponse.validfail(false,"上传文件失败");
        }
        return RestResponse.success(true );
    }

    /**
    * @Description:  清理分块
    * @Param: [chunkFileFolderPath, chunkTotal]
    * @Author: stefanie
    * @Date: 2023/10/2~12:18
    */
    public void clearChunkFiles(String chunkFileFolderPath,int chunkTotal){
        List<DeleteObject> collect = Stream.iterate(0, i -> i++).limit(chunkTotal).map(i -> new DeleteObject(chunkFileFolderPath + i)).collect(Collectors.toList());
        RemoveObjectsArgs build = RemoveObjectsArgs.builder()
                .bucket(bucket_video)
                .objects(collect)
                .build();
        Iterable<Result<DeleteError>> results = minioClient.removeObjects(build);
        results.forEach(r->{
                DeleteError deleteError=null;
            try {
                deleteError=r.get();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("清除分块文件失败");
            }
        });
    }


    /**
    * @Description:  合并分块
    * @Param: [companyId, fileMd5, chunkTotal, uploadFileParamsDto]
    * @Author: stefanie
    * @Date: 2023/10/2~11:36
    */
    @Override
    public RestResponse mergeChunks(Long companyId, String fileMd5, int chunkTotal, UploadFileParamsDto uploadFileParamsDto) {
        //1.找到分块文件调用minio的sdk进行分块合并
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        //1.1找到所有的分块文件
        List<ComposeSource> collect = Stream.iterate(0, i -> ++i).limit(chunkTotal).map(i -> ComposeSource.builder().bucket(bucket_video).object(chunkFileFolderPath+i).build()).collect(Collectors.toList());
        String objectName=getFilePath(fileMd5,uploadFileParamsDto.getFilename().substring(uploadFileParamsDto.getFilename().lastIndexOf(".")));
        ComposeObjectArgs build = ComposeObjectArgs.builder().bucket(bucket_video)
                .object(objectName)
                .sources(collect)
                .build();
        try {
            minioClient.composeObject(build);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("合并文件出错，bucket:{},objectName:{},错误信息：{}",bucket_video,objectName,e.getMessage());
            return RestResponse.validfail(false,"合并文件异常");
        }
        //2.校验合并后的文件和源文件是否一致
        //2.1先下载文件
        File file = downloadFileFromMinio(bucket_video, objectName);
        //2.2 计算合并后的文件的MD5
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            String merge_file_md5 = DigestUtils.md5Hex(fileInputStream);
            //2.3 比较
            if(!fileMd5.equals(merge_file_md5)){
                log.error("校验合并文件MD5不一致，源文件MD5:{},合并后文件MD5:{},objectName:{}",fileMd5,merge_file_md5,objectName);
                return RestResponse.validfail(false,"文件校验失败");
            }
            uploadFileParamsDto.setFileSize(file.length());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.validfail(false,"文件校验失败");
        }
        //3.文件信息入库(事务需用代理对象调用)
        MediaFiles mediaFiles = currentProxy.addMediaFilesToDb(companyId, fileMd5, uploadFileParamsDto, bucket_video, objectName);
        if (mediaFiles==null){
            return RestResponse.validfail(false,"文件入库失败");
        }
        //4.清理分块文件
        clearChunkFiles(chunkFileFolderPath,chunkTotal);
        return RestResponse.success(true);
    }
}
