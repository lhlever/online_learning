package com.stefanie;

import com.stefanie.content.courseMapper.TeachplanMapper;
import com.stefanie.content.service.CourseCategoryService;
import com.stefanie.content.service.TeachplanService;
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
    @Autowired
    private TeachplanService teachplanService;

    @Autowired
    private TeachplanMapper teachplanMapper;
    @Test
    public void testTree(){
        courseCategoryService.getTreeCourse();
    }

    @Test
    void testTeachPlan(){
//        teachplanService.getTreeNodes(117L);
        teachplanMapper.selectTreeNodes(117L).forEach(i-> System.out.println(i));
    }
}
