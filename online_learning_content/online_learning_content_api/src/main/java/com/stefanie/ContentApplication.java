package com.stefanie;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author：stefanie
 * @Package：com.stefanie
 * @Project：online_learning
 * @name：ContentApplication
 * @Date：2023/9/17 16:11
 * @Filename：ContentApplication
 */
@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.stefanie.content.feignClient"})
@MapperScan({"com.stefanie.online_learning_content_service.courseMapper"})
@ComponentScan({"com.stefanie","com.xuecheng.messagesdk"})
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }
}
