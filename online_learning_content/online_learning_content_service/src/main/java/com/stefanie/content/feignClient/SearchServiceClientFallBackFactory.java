package com.stefanie.content.feignClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author：stefanie
 * @Package：com.stefanie.content.feignClient
 * @Project：online_learning
 * @name：SearchServiceClientFallBackFactory
 * @Date：2023/10/5 21:23
 * @Filename：SearchServiceClientFallBackFactory
 * @Description：
 */
@Component
@Slf4j
public class SearchServiceClientFallBackFactory implements FallbackFactory<SearchServiceClient> {
    @Override
    public SearchServiceClient create(Throwable throwable) {
        return new SearchServiceClient() {
            @Override
            public Boolean add(CourseIndex courseIndex) {
                log.error("添加课程索引接口发生熔断：{}",throwable.toString());
                return false;
            }
        };
    }
}
