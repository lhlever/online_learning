package com.stefanie.online_learning_content_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stefanie.domain.PageParam;
import com.stefanie.domain.PageResult;
import com.stefanie.exception.GlobalException;
import com.stefanie.online_learning_content_model.dto.AddCourseDto;
import com.stefanie.online_learning_content_model.dto.CourseBaseInfoDto;
import com.stefanie.online_learning_content_model.dto.EditCourseDto;
import com.stefanie.online_learning_content_model.dto.QueryCourseParamDto;
import com.stefanie.online_learning_content_model.po.CourseBase;
import com.stefanie.online_learning_content_model.po.CourseCategory;
import com.stefanie.online_learning_content_model.po.CourseMarket;
import com.stefanie.online_learning_content_service.courseMapper.CourseCategoryMapper;
import com.stefanie.online_learning_content_service.courseMapper.CourseMarketMapper;
import com.stefanie.online_learning_content_service.service.CourseBaseService;
import com.stefanie.online_learning_content_service.courseMapper.CourseBaseMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
* @author stefanie
* @description 针对表【course_base(课程基本信息)】的数据库操作Service实现
* @createDate 2023-09-17 21:44:20
*/
@Slf4j
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase>
    implements CourseBaseService{

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Autowired
    private CourseMarketMapper courseMarketMapper;
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;
    @Override
    public PageResult<CourseBase> getPageByCondition(PageParam pageParam, QueryCourseParamDto queryCourseParamDto) {
        Page<CourseBase> courseBasePage = new Page<CourseBase>(pageParam.getPageNo(), pageParam.getPageSize());
        LambdaQueryWrapper<CourseBase> courseBaseLambdaQueryWrapper = new LambdaQueryWrapper<CourseBase>();
        courseBaseLambdaQueryWrapper.like(
                StringUtils.isNoneEmpty(queryCourseParamDto.getCourseName()),
                CourseBase::getName,
                queryCourseParamDto.getCourseName())
                .eq(StringUtils.isNotEmpty(queryCourseParamDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamDto.getAuditStatus())
                .eq(StringUtils.isNotEmpty(queryCourseParamDto.getPublishStatus()), CourseBase::getStatus,queryCourseParamDto.getPublishStatus());
        courseBaseMapper.selectPage(courseBasePage,courseBaseLambdaQueryWrapper);
        val courseBasePageResult = new PageResult<CourseBase>();
        courseBasePageResult.setItems(courseBasePage.getRecords());
        courseBasePageResult.setPage(courseBasePage.getCurrent());
        courseBasePageResult.setCounts(courseBasePage.getTotal());
        courseBasePageResult.setPageSize(courseBasePage.getSize());
        return  courseBasePageResult;
    }

    @Transactional//增删改的方法加上事务
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {
        log.error(addCourseDto+"==============");
        //0.参数合法性校验
        //TODO 合法性校验
        //1.向课程基本表course_base写入数据
        CourseBase courseBase = new CourseBase();
        //1.1将页面传入的参数放到courseBaseNew对象
        /*courseBase.setName(addCourseDto.getName());
        courseBase.setDescription(addCourseDto.getDescription());
        这种太费劲了，使用BeanUtils*/
        BeanUtils.copyProperties(addCourseDto,courseBase);//只要属性名称一致就可以拷贝
        courseBase.setCompanyId(companyId);
        //1.2设置修改时间
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setAuditStatus("202002");
        courseBase.setStatus("203001");
        int insert = courseBaseMapper.insert(courseBase);
        if (insert<=0){
            throw  new RuntimeException ("添加课程失败");
        }
        //2.向课程营销表course_market写入数据
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto,courseMarket);
        log.debug(courseMarket.getPrice()+"============================");
        Long CourseId = courseBase.getId();
        courseMarket.setId(CourseId);
        saveCourseMaket(courseMarket);
        CourseBaseInfoDto courseBaseInfoDto = getCourseBaseInfoDto(CourseId);
        //查询课程分类的名称
        CourseCategory courseCategory = courseCategoryMapper.selectById(addCourseDto.getMt());
        CourseCategory courseCategory1 = courseCategoryMapper.selectById(addCourseDto.getSt());
        courseBaseInfoDto.setMtName(courseCategory.getLabel());
        courseBaseInfoDto.setStName(courseCategory1.getLabel());
//        BeanUtils.copyProperties(courseCategory,courseBaseInfoDto);
        return courseBaseInfoDto;
    }
    /**
    * @Description:  单独写一个保存营销信息的方法，逻辑：存在就更新，不存在就插入
    * @Param: [courseMarket]
    * @Author: stefanie
    * @Date: 2023/9/19~12:56
    */
    private int saveCourseMaket(CourseMarket courseMarket){
        //参数合法性校验
        if (StringUtils.isEmpty(courseMarket.getCharge())){
            throw new RuntimeException("收费规则为空");
        }
        //如果收费，价格没有填写抛异常
        if (courseMarket.getCharge().equals("201001")){
            if(courseMarket.getPrice()==null||courseMarket.getPrice()<=0){
                log.error(courseMarket.getPrice()+"-------------");
                GlobalException.cast("价格没有填写or价格不合法");
            }
        }
        CourseMarket courseMarket1 = courseMarketMapper.selectById(courseMarket.getId());
        int result;
        if (courseMarket1==null){
            //插入数据库
            result=courseMarketMapper.insert(courseMarket);
        }else{
            BeanUtils.copyProperties(courseMarket,courseMarket1);
            courseMarket1.setId(courseMarket.getId());
            //更新
            result=courseMarketMapper.updateById(courseMarket1);
        }

        return result;
    }

    public CourseBaseInfoDto getCourseBaseInfoDto(Long courseId){
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase==null){
            return null;
        }
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if (courseMarket==null){
            return null;
        }
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        return courseBaseInfoDto;
    }

    @Override
    public CourseBaseInfoDto updateCourseBaseInfoDto(Long companyId, EditCourseDto editCourseDto) {
        Long courseId = editCourseDto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase==null){
            GlobalException.cast("修改课程不存在");
        }
        if(!companyId.equals(courseBase.getCompanyId())){
            GlobalException.cast("您无修改本机构权限");
        }
        BeanUtils.copyProperties(editCourseDto,courseBase);
        courseBase.setChangeDate(LocalDateTime.now());
        int i = courseBaseMapper.updateById(courseBase);
        if (i<=0){
            GlobalException.cast("修改课程基本信息失败");
        }
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if (courseBase==null){
            GlobalException.cast("修改课程不存在");
        }
        BeanUtils.copyProperties(editCourseDto,courseMarket);
        int j = courseMarketMapper.updateById(courseMarket);
        if (j<=0){
            GlobalException.cast("修改课程营销信息失败");
        }

        CourseBaseInfoDto courseBaseInfoDto = getCourseBaseInfoDto(courseId);
        return courseBaseInfoDto;
    }


}




