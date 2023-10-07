package com.xuecheng.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcUser;
import com.xuecheng.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.ucenter.service.impl
 * @Project：online_learning
 * @name：UserServiceImpl
 * @Date：2023/10/6 14:35
 * @Filename：UserServiceImpl
 * @Description：
 */
@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    XcUserMapper xcUserMapper;

    @Autowired
    ApplicationContext applicationContext;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //0. 将传入的json转成AuthParamDto对象
        AuthParamsDto authParamsDto=null;
        try {
            authParamsDto=JSON.parseObject(username,AuthParamsDto.class);
        }catch (Exception w){
            throw  new RuntimeException("请求认证参数不符合要求");
        }
        //1获取认证类型
        String authType = authParamsDto.getAuthType();
        String beanName=authType+"_authservice";
        //2.根据认证类型从，ioc容器中取出对应的处理bean----------策略模式
        AuthService authService = applicationContext.getBean(beanName, AuthService.class);
        //3.调用统一的execute方法完成认证
        XcUserExt execute = authService.execute(authParamsDto);
        //4.封装XcUserExt为UserDetails
        return getUserDetials(execute);
    }

    /**
    * @Description:  封装XcUserExt为UserDetails
    * @Param: [xcUserExt]
    * @Author: stefanie
    * @Date: 2023/10/6~16:44
    */
    public UserDetails getUserDetials(XcUserExt xcUserExt){
        String[] authorities={"test"};
        String pass=xcUserExt.getPassword();
        //4.使用token令牌扩展用户信息
        xcUserExt.setPassword(null);
        String jsonString = JSON.toJSONString(xcUserExt);
        UserDetails user = User.withUsername(jsonString).password(pass).authorities(authorities).build();
        return user;
    }
}
