package com.stefanie.online_learning_content_service.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.exception.GlobalException;
import com.stefanie.online_learning_content_model.dto.CourseBaseInfoDto;
import com.stefanie.online_learning_content_model.dto.CoursePreviewDto;
import com.stefanie.online_learning_content_model.dto.TeachPlanDto;
import com.stefanie.online_learning_content_model.po.CourseBase;
import com.stefanie.online_learning_content_model.po.CourseMarket;
import com.stefanie.online_learning_content_model.po.CoursePublish;
import com.stefanie.online_learning_content_model.po.CoursePublishPre;
import com.stefanie.online_learning_content_service.courseMapper.CourseBaseMapper;
import com.stefanie.online_learning_content_service.courseMapper.CourseMarketMapper;
import com.stefanie.online_learning_content_service.courseMapper.CoursePublishPreMapper;
import com.stefanie.online_learning_content_service.service.*;
import com.stefanie.online_learning_content_service.courseMapper.CoursePublishMapper;
import com.xuecheng.messagesdk.service.MqMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
* @author stefanie
* @description 针对表【course_publish(课程发布)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:20
*/
@Service
public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish>
    implements CoursePublishService{
    @Autowired
    CourseBaseService courseBaseService;

    @Autowired
    TeachplanService teachplanService;

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CoursePublishPreMapper coursePublishPreMapper;

    @Autowired
    CoursePublishMapper coursePublishMapper;

    @Autowired
    MqMessageService mqMessageService;
    /**
    * @Description:  获取课程预览信息
    * @Param: [courseId]
    * @Author: stefanie
    * @Date: 2023/10/4~15:07
    */
    @Override
    public CoursePreviewDto getCoursePreviewDto(Long courseId) {
        CoursePreviewDto coursePreviewDto = new CoursePreviewDto();
        //查询课程基本信息以及营销信息
        CourseBaseInfoDto courseBaseInfoDto = courseBaseService.getCourseBaseInfoDto(courseId);
        //课程计划信息
        List<TeachPlanDto> treeNodes = teachplanService.getTreeNodes(courseId);
        coursePreviewDto.setCourseBase(courseBaseInfoDto);
        coursePreviewDto.setTeachPlans(treeNodes);
        return coursePreviewDto;
    }

    @Transactional
    @Override
    public void commitAudit(Long companyId, Long courseId) {
        //1.如果课程审核状态为已提交则不允许提交,只有未提交或审核通过才允许提交
        CourseBaseInfoDto courseBaseInfo = courseBaseService.getCourseBaseInfoDto(courseId);
        if (courseBaseInfo==null){
            GlobalException.cast("课程找不到");
        }
        if("202003".equals(courseBaseInfo.getAuditStatus())){
            GlobalException.cast("课程已提交请等待审核");
        }
        //2.1课程的图片没有填写也不允许提交
        if(StringUtils.isEmpty(courseBaseInfo.getPic())){
            GlobalException.cast("请上传课程封面");
        }
        //2.2 计划信息没有填写也不允许提交
        List<TeachPlanDto> treeNodes = teachplanService.getTreeNodes(courseId);
        if (treeNodes==null||treeNodes.size()==0){
            GlobalException.cast("请编写课程计划");
        }
        //3.查询到课程基本信息、营销信息、课程计划等信息插入到课程预发布表
        CoursePublishPre coursePublishPre = new CoursePublishPre();
        BeanUtils.copyProperties(courseBaseInfo,coursePublishPre);
        coursePublishPre.setCompanyId(companyId);
        // TODO: 2023/10/4 本机构只能提交本机构的课程
        //3.1营销信息转json
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        String courseMarketJson = JSON.toJSONString(courseMarket);
        coursePublishPre.setMarket(courseMarketJson);
        //3.2 课程计划转json
        String treeNodesJson = JSON.toJSONString(treeNodes);
        coursePublishPre.setTeachplan(treeNodesJson);
        coursePublishPre.setStatus("202003");
        coursePublishPre.setCreateDate(LocalDateTime.now());
        //4.插入预发布表
        //4.1查询预发布表，如果有记录则更新，没有则插入
        CoursePublishPre coursePublishPre1 = coursePublishPreMapper.selectById(courseId);
        if (coursePublishPre1==null){
            coursePublishPreMapper.insert(coursePublishPre);
        }else{
            coursePublishPreMapper.updateById(coursePublishPre);
        }
        //5.更新课程基本信息表的审核状态为已提交
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        courseBase.setAuditStatus("202003");
        courseBaseMapper.updateById(courseBase);
    }

    @Transactional
    @Override
    public void publish(Long companyId, Long courseId) {
         //向课程发布表写入数据
        //查询预发布表
        CoursePublishPre coursePublishPre = coursePublishPreMapper.selectById(courseId);
        if (coursePublishPre!=null){
            GlobalException.cast("课程没有审核记录，不允许发布");
        }
        String status = coursePublishPre.getStatus();
        //如果课程没有审核通过不允许发布
        if (!"202004".equals(status)){
            GlobalException.cast("课程没有审核通过不允许发布");
        }
        CoursePublish coursePublish = new CoursePublish();
        BeanUtils.copyProperties(coursePublishPre,coursePublish);
        //有则更新没有则插入
        CoursePublish coursePublish1 = coursePublishMapper.selectById(courseId);
        if (coursePublish1==null){
            coursePublishMapper.insert(coursePublish);
        }else{
            coursePublishMapper.updateById(coursePublish);
        }
        //向消息表写入数据
        mqMessageService.addMessage("course_publish", String.valueOf(courseId),null,null);
        //将预发布表数据删除
        coursePublishPreMapper.deleteById(courseId);
    }
}




