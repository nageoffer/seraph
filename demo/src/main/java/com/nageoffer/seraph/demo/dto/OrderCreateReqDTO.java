package com.nageoffer.seraph.demo.dto;

import lombok.Data;

/**
 * 订单创建入参请求实体
 *
 * @公众号：马丁玩编程，回复：加群，添加马哥微信（备注：幂等）获取项目资料
 */
@Data
public class OrderCreateReqDTO {

    private String username;

    private String orderSn;
}
