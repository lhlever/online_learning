package com.stefanie.content.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stefanie.online_learning_content_model.dto.BindTeachPlanMediaDto;
import com.stefanie.online_learning_content_model.dto.SaveTeachplanDto;
import com.stefanie.online_learning_content_model.dto.TeachPlanDto;
import com.stefanie.online_learning_content_model.po.Teachplan;

import java.util.List;

/**
* @author stefanie
* @description 针对表【teachplan(课程计划)】的数据库操作Service
* @createDate 2023-09-17 21:44:21
*/
public interface TeachplanService extends IService<Teachplan> {
    /**
    * @Description:  根据课程id查询课程计划
    * @Param: [courseId]
    * @Author: stefanie
    * @Date: 2023/9/20~21:38
    */
    public List<TeachPlanDto> getTreeNodes(Long courseId);

    /**
    * @Description:  新增/修改/保存课程计划
    * @Param: [saveTeachplanDto]
    * @Author: stefanie
    * @Date: 2023/9/20~21:39
    */
    public void saveTeachPlan(SaveTeachplanDto saveTeachplanDto);

    /**
    * @Description:  绑定课程信息与媒资
    * @Param: [bindTeachPlanMediaDto]
    * @Author: stefanie
    * @Date: 2023/10/4~8:44
    */
    public void associationMedia(BindTeachPlanMediaDto bindTeachPlanMediaDto);
}
