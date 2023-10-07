package com.xuecheng.ucenter.service.impl;

import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.ucenter.service.impl
 * @Project：online_learning
 * @name：WxAuthServiceImpl
 * @Date：2023/10/6 16:26
 * @Filename：WxAuthServiceImpl
 * @Description：微信扫码认证
 */
@Service("wx_authservice")
public class WxAuthServiceImpl implements AuthService {
    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {
        return null;
    }
}
