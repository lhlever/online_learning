package com.stefanie.online_learning_content_service.courseMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stefanie.online_learning_content_model.dto.TeachPlanDto;
import com.stefanie.online_learning_content_model.po.Teachplan;

import java.util.List;

/**
* @author stefanie
* @description 针对表【teachplan(课程计划)】的数据库操作Mapper
* @createDate 2023-09-17 21:44:21
* @Entity generator.domain.Teachplan
*/
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    /**
    * @Description:  查询某课程的课程计划，组成树形结构
    * @Param: [courseId]
    * @Author: stefanie
    * @Date: 2023/9/20~19:45
    */
    public List<TeachPlanDto> selectTreeNodes(Long courseId);
}




