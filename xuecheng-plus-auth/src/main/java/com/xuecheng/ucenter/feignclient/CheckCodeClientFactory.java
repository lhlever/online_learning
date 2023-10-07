package com.xuecheng.ucenter.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author：stefanie
 * @Package：com.xuecheng.ucenter.feignclient
 * @Project：online_learning
 * @name：CheckCodeClientFactory
 * @Date：2023/10/6 21:11
 * @Filename：CheckCodeClientFactory
 * @Description：
 */
@Slf4j
@Component
public class CheckCodeClientFactory implements FallbackFactory<CheckCodeClient> {
    public CheckCodeClient create(Throwable throwable) {
        return new CheckCodeClient() {
            public Boolean verify(String key, String code) {
                log.debug("调用验证码服务熔断异常:{}", throwable.getMessage());
                return null;
            }
        };
    }
}

