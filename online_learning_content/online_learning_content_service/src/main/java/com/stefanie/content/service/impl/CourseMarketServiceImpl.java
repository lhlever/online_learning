package com.stefanie.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.online_learning_content_model.po.CourseMarket;
import com.stefanie.content.service.CourseMarketService;
import com.stefanie.content.courseMapper.CourseMarketMapper;
import org.springframework.stereotype.Service;

/**
* @author stefanie
* @description 针对表【course_market(课程营销信息)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:21
*/
@Service
public class CourseMarketServiceImpl extends ServiceImpl<CourseMarketMapper, CourseMarket>
    implements CourseMarketService{

}




