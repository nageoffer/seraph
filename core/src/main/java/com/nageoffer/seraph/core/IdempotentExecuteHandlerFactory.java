/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nageoffer.seraph.core;

import com.nageoffer.seraph.api.enums.IdempotentSceneEnum;
import com.nageoffer.seraph.api.enums.IdempotentTypeEnum;

/**
 * 幂等执行处理器工厂
 * <p>
 * Q：可能会有同学有疑问：这里为什么要采用简单工厂模式？策略模式不行么？
 * A：策略模式同样可以达到获取真正幂等处理器功能。但是简单工厂的语意更适合这个场景，所以选择了简单工厂
 *
 * @公众号：马丁玩编程，回复：加群，添加马哥微信（备注：幂等）获取项目资料
 */
public final class IdempotentExecuteHandlerFactory {

    /**
     * 获取幂等执行处理器
     *
     * @param scene 指定幂等验证场景类型
     * @param type  指定幂等处理类型
     * @return 幂等执行处理器
     */
    public static IdempotentExecuteHandler getInstance(IdempotentSceneEnum scene, IdempotentTypeEnum type) {
        IdempotentExecuteHandler result;
        switch (scene) {
            case HTTP:
                switch (type) {
                    case PARAM:
                        result = ApplicationContextHolder.getBean(IdempotentParamService.class);
                        break;
                    case TOKEN:
                        result = ApplicationContextHolder.getBean(IdempotentTokenService.class);
                        break;
                    case SPEL:
                        result = ApplicationContextHolder.getBean(IdempotentSpELHttpService.class);
                        break;
                    default:
                        throw new RuntimeException(String.format("幂等处理类型 [%s] 不存在", type.name()));
                }
                break;
            case MQ:
                result = ApplicationContextHolder.getBean(IdempotentSpELMQService.class);
                break;
            default:
                throw new RuntimeException(String.format("幂等场景类型 [%s] 不存在", scene.name()));
        }
        return result;
    }
}
