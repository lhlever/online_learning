package com.stefanie;

import com.stefanie.online_learning_content_service.service.CourseBaseService;
import com.stefanie.online_learning_content_service.service.CourseCategoryService;
import com.stefanie.online_learning_content_service.service.impl.CourseCategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author：stefanie
 * @Package：com.stefanie.online_learning_content_service
 * @Project：online_learning
 * @name：courseApplication
 * @Date：2023/9/18 20:26
 * @Filename：courseApplication
 * @Description：
 */
@SpringBootTest
public class courseApplication {
    @Autowired
    private CourseCategoryService courseCategoryService;
    @Test
    public void testTree(){
        courseCategoryService.getTreeCourse();
    }
}
