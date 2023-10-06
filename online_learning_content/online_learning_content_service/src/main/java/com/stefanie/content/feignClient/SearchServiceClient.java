package com.stefanie.content.feignClient;

import okhttp3.Cache;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author：stefanie
 * @Package：com.stefanie.content.feignClient
 * @Project：online_learning
 * @name：SearchServiceClient
 * @Date：2023/10/5 21:16
 * @Filename：SearchServiceClient
 * @Description：
 */
@FeignClient(value = "search",fallbackFactory = SearchServiceClientFallBackFactory.class)
public interface SearchServiceClient {

    @PostMapping("/search/index/course")
    public Boolean add(@RequestBody CourseIndex courseIndex);
}
