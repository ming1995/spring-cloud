package com.ljm.cloud.feign.config;


import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Feign配置类
 * @author lijunming
 * @date 2018/9/22 下午1:06
 */
public class FeignConfiguration {
    /**
     *将契约改为feign原生的默认契约，这样就可以使用feign自带的注解类
     * return  默认的feign契约
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     *http Basic的认证
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("ming", "19951224");
    }
}
