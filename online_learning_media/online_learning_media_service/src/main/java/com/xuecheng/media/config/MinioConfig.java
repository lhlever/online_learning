package com.xuecheng.media.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.media.config
 * @Project：online_learning
 * @name：Mini0Config
 * @Date：2023/10/1 15:56
 * @Filename：Mini0Config
 * @Description：
 */
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder().endpoint(endpoint)
                .credentials(accessKey,secretKey)
                .build();
    }
}
