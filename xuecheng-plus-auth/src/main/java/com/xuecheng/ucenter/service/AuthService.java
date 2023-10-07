package com.xuecheng.ucenter.service;

import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.ucenter.service
 * @Project：online_learning
 * @name：AuthService
 * @Date：2023/10/6 16:15
 * @Filename：AuthService
 * @Description：
 */
public interface AuthService {
    /**
    * @Description:  统一的认证方法
    * @Param: [authParamsDto]
    * @Author: stefanie
    * @Date: 2023/10/6~16:17
    */
    XcUserExt execute(AuthParamsDto authParamsDto);
}
