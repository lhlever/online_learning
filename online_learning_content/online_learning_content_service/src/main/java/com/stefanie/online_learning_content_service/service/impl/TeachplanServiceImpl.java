package com.stefanie.online_learning_content_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.exception.GlobalException;
import com.stefanie.online_learning_content_model.dto.BindTeachPlanMediaDto;
import com.stefanie.online_learning_content_model.dto.SaveTeachplanDto;
import com.stefanie.online_learning_content_model.dto.TeachPlanDto;
import com.stefanie.online_learning_content_model.po.Teachplan;
import com.stefanie.online_learning_content_model.po.TeachplanMedia;
import com.stefanie.online_learning_content_service.courseMapper.TeachplanMediaMapper;
import com.stefanie.online_learning_content_service.service.TeachplanService;
import com.stefanie.online_learning_content_service.courseMapper.TeachplanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author stefanie
* @description 针对表【teachplan(课程计划)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:21
*/
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan>
    implements TeachplanService{

    @Autowired
    private TeachplanMapper teachplanMapper;

    @Autowired
    private TeachplanMediaMapper teachplanMediaMapper;
    @Override
    public List<TeachPlanDto> getTreeNodes(Long courseId) {
        List<TeachPlanDto> teachPlanDtos = teachplanMapper.selectTreeNodes(courseId);
        return teachPlanDtos;
    }

    @Override
    public void saveTeachPlan(SaveTeachplanDto saveTeachplanDto) {
        //通过是否含有课程计划id判断是新增还是修改
        Long id = saveTeachplanDto.getId();
        if (id==null){
            //新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            //确定排序字段（找到同级节点个数，排序字段就是个数加1）
            LambdaQueryWrapper<Teachplan> teachplanLambdaQueryWrapper = new LambdaQueryWrapper<>();
            teachplanLambdaQueryWrapper.eq(Teachplan::getCourseId,saveTeachplanDto.getCourseId()).eq(Teachplan::getParentid,saveTeachplanDto.getParentid());
            Integer integer = teachplanMapper.selectCount(teachplanLambdaQueryWrapper);
            teachplan.setOrderby(integer+1);
            teachplanMapper.insert(teachplan);
        }else{
            //修改
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }
    }

    /**
    * @Description:  绑定课程信息与媒资
    * @Param: [bindTeachPlanMediaDto]
    * @Author: stefanie
    * @Date: 2023/10/4~8:46
    */
    @Transactional
    @Override
    public void associationMedia(BindTeachPlanMediaDto bindTeachPlanMediaDto) {
        //先删除原有记录，根据课程计划id删除它所绑定的媒资
        int delete = teachplanMediaMapper.delete(new LambdaQueryWrapper<>(TeachplanMedia.class).eq(TeachplanMedia::getTeachplanId, bindTeachPlanMediaDto.getTeachPlanId()));
        //在添加新纪录
        TeachplanMedia teachplanMedia = new TeachplanMedia();
        BeanUtils.copyProperties(bindTeachPlanMediaDto,teachplanMedia);
        //查询课程id
        Teachplan teachplan = teachplanMapper.selectById(bindTeachPlanMediaDto.getTeachPlanId());
        if (teachplan==null){
            GlobalException.cast("课程计划不存在");
            return;
        }
        teachplanMedia.setCourseId(teachplan.getCourseId());
        teachplanMedia.setMediaFilename(bindTeachPlanMediaDto.getFileName());
        teachplanMediaMapper.insert(teachplanMedia);
    }
}




