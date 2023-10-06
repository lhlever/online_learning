package com.stefanie.content.feignClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_service.feignClient
 * @Project：online_learning
 * @name：MediaServiceClientFallbackFactory
 * @Date：2023/10/5 12:44
 * @Filename：MediaServiceClientFallbackFactory
 * @Description：
 */
@Component
@Slf4j
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {
    @Override
    public MediaServiceClient create(Throwable throwable) {
        return new MediaServiceClient() {
            //发生熔断时，调用此方法执行降级
            @Override
            public String upload(MultipartFile multipartFile, String objectname) throws IOException {
                log.error("调用远程上传文件接口发生熔断：{}",throwable.toString());
                return null;
            }
        };
    }
}
