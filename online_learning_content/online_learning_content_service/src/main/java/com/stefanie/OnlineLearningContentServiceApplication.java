package com.stefanie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stefanie.online_learning_content_service.courseMapper")
public class OnlineLearningContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningContentServiceApplication.class, args);
    }

}
