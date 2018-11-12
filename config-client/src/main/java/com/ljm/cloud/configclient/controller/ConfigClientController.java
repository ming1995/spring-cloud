package com.ljm.cloud.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijunming
 * @date 2018/10/15 下午11:36
 */
@RestController
@RefreshScope   //该注解用于配置更改时得到特殊处理
public class ConfigClientController {

    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String hello(){
        return this.profile;
    }
}
