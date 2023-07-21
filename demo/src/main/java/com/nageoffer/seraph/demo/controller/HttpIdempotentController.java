package com.nageoffer.seraph.demo.controller;

import com.nageoffer.seraph.api.annotation.Idempotent;
import com.nageoffer.seraph.api.enums.IdempotentSceneEnum;
import com.nageoffer.seraph.api.enums.IdempotentTypeEnum;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP 方式测试幂等组件
 *
 * @公众号：马丁玩编程，回复：加群，添加马哥微信（备注：幂等）获取项目资料
 */
@RestController
public class HttpIdempotentController {

    @SneakyThrows
    @GetMapping("/idempotent/http/test")
    @Idempotent(
            scene = IdempotentSceneEnum.HTTP,
            type = IdempotentTypeEnum.PARAM,
            message = "用户触发幂等行为，请稍后再试"
    )
    public String idempotentHttpTest(@RequestParam("orderSn") String orderSn) {
        Thread.sleep(10000);
        System.out.printf("[%s] 当前线程执行幂等测试行为...%n", Thread.currentThread().getName());
        return "success";
    }
}
