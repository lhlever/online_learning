package com.xuecheng.media.api;

import com.stefanie.domain.RestResponse;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.media.api
 * @Project：online_learning
 * @name：MediaOpenController
 * @Date：2023/10/4 16:03
 * @Filename：MediaOpenController
 * @Description：
 */
@RestController
@RequestMapping("/open")
public class MediaOpenController {
    @Autowired
    private MediaFileService mediaFileService;
    @GetMapping("/preview/{mediaId}")
    public RestResponse<String> getPlayUrlByMediaId(@PathVariable String mediaId){
        MediaFiles mediaFilesById = mediaFileService.getMediaFilesById(mediaId);
        if(mediaFilesById==null){
            return RestResponse.validfail("找不到该视频");
        }
        String url = mediaFilesById.getUrl();
        if (StringUtils.isEmpty(url)){
            return RestResponse.validfail("该视频正在转码中");
        }
        return  RestResponse.success(mediaFilesById.getUrl());


    }
}
