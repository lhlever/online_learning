package com.stefanie.content.feignClient;

import com.stefanie.content.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_service.feignClient
 * @Project：online_learning
 * @name：MediaServiceClient
 * @Date：2023/10/5 12:08
 * @Filename：MediaServiceClient
 * @Description：
 */
@FeignClient(value = "media-api",configuration = {MultipartSupportConfig.class},fallbackFactory = MediaServiceClientFallbackFactory.class)
@Component
public interface MediaServiceClient {
    @RequestMapping(value = "/upload/coursefile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestPart("filedata") MultipartFile multipartFile, @RequestParam("objectname")String objectname) throws IOException ;
}
