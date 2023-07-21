package com.nageoffer.seraph.demo.config;

import com.nageoffer.seraph.core.ApplicationContextHolder;
import com.nageoffer.seraph.core.IdempotentAspect;
import com.nageoffer.seraph.http.IdempotentParamExecuteHandler;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @公众号：马丁玩编程，回复：加群，添加马哥微信（备注：幂等）获取项目资料
 */
@Configuration
public class SeraphConfig {

    @Bean
    public IdempotentParamExecuteHandler idempotentParamExecuteHandler(RedissonClient redissonClient) {
        return new IdempotentParamExecuteHandler(redissonClient);
    }

    @Bean
    public IdempotentAspect idempotentAspect() {
        return new IdempotentAspect();
    }

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }
}
