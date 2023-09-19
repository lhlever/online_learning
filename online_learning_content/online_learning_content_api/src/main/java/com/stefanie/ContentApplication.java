package com.stefanie;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author：stefanie
 * @Package：com.stefanie
 * @Project：online_learning
 * @name：ContentApplication
 * @Date：2023/9/17 16:11
 * @Filename：ContentApplication
 */
@EnableSwagger2Doc
@SpringBootApplication
@MapperScan({"com.stefanie.online_learning_content_service.courseMapper"})
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }
}
