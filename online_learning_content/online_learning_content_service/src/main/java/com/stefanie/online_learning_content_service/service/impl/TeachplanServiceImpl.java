package com.stefanie.online_learning_content_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.online_learning_content_model.po.Teachplan;
import com.stefanie.online_learning_content_service.service.TeachplanService;
import com.stefanie.online_learning_content_service.courseMapper.TeachplanMapper;
import org.springframework.stereotype.Service;

/**
* @author stefanie
* @description 针对表【teachplan(课程计划)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:21
*/
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan>
    implements TeachplanService{

}




