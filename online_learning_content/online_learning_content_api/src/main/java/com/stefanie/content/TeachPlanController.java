package com.stefanie.content;

import com.stefanie.online_learning_content_model.dto.BindTeachPlanMediaDto;
import com.stefanie.online_learning_content_model.dto.SaveTeachplanDto;
import com.stefanie.online_learning_content_model.dto.TeachPlanDto;
import com.stefanie.online_learning_content_service.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：stefanie
 * @Package：com.stefanie.content
 * @Project：online_learning
 * @name：TeachPlanController
 * @Date：2023/9/20 19:14
 * @Filename：TeachPlanController
 * @Description：课程计划管理
 */
@RestController
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
public class TeachPlanController {
    @Autowired
    private TeachplanService teachplanService;

    /**
    * @Description:  查询课程计划
    * @Param: [courseId]
    * @Author: stefanie
    * @Date: 2023/9/20~19:17
    */
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    @ApiOperation("查询课程计划树形结构")
    public List<TeachPlanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.getTreeNodes(courseId);
    }
    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachPlan(@RequestBody SaveTeachplanDto saveTeachplanDto){
        teachplanService.saveTeachPlan(saveTeachplanDto);
    }

    @ApiOperation("课程计划和媒资信息绑定")
    @PostMapping("/teachplan/association/media")
    public void associationMedia(@RequestBody BindTeachPlanMediaDto bindTeachPlanMediaDto){
        teachplanService.associationMedia(bindTeachPlanMediaDto);
    }

}
