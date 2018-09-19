package com.ljm.cloud.consumer.ribbon;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijunming
 * @date 2018/9/19 下午11:06
 * 为微服务名称为user的配置负载均衡规则
 */
@Configuration
@RibbonClient(name="user",configuration=RibbonConfiguration.class)
public class RibbonForUser {
}
