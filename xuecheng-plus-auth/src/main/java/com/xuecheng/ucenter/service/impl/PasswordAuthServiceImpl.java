package com.xuecheng.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.AuthApplication;
import com.xuecheng.ucenter.feignclient.CheckCodeClient;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcUser;
import com.xuecheng.ucenter.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.ucenter.service.impl
 * @Project：online_learning
 * @name：PasswordAuthServiceImpl
 * @Date：2023/10/6 16:25
 * @Filename：PasswordAuthServiceImpl
 * @Description：账号名密码
 */
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {
    @Autowired
    XcUserMapper xcUserMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CheckCodeClient checkCodeClient;
    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {
        //1.根据用户名查询数据库
        XcUser xcUser = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, authParamsDto.getUsername()));
        //2.如果用户不存在，返回null即可，springsecurity框架抛出异常（用户不存在）
        if (xcUser==null){
            throw new RuntimeException("账户不存在");
        }
        //2.1.检查验证码是否正确
        // TODO: 2023/10/6  认证时检查验证码是否正确
        String checkcode = authParamsDto.getCheckcode();
        String checkcodekey = authParamsDto.getCheckcodekey();
        if (StringUtils.isEmpty(checkcodekey)||StringUtils.isEmpty(checkcode)){
            throw new RuntimeException("请输入验证码");
        }
        Boolean verify = checkCodeClient.verify(checkcodekey, checkcode);
        if (verify==null||!verify){
            throw new RuntimeException("验证码错误");
        }

        //3.验证密码是否正确
        //3.1数据库中正确密码
        String password=xcUser.getPassword();
        //3.2用户输入的密码
        String passwordForm=authParamsDto.getPassword();
        boolean matches = passwordEncoder.matches(passwordForm, password);
        if (!matches){
            throw  new RuntimeException("账户或密码错误");
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(xcUser,xcUserExt);

        return xcUserExt;
    }
}
