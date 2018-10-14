package com.ljm.cloud.feign.config;


import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
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
        return new BasicAuthRequestInterceptor("user", "19951224");
    }

    /**
     *配置日志输出级别
     * NONE:		不记录任何日志
     * BASIC:		仅记录请求方法，url,响应状态代码以及执行时间
     * HEADERS:	记录BASIC级别的基础上，记录请求和响应的header
     * FULL:		记录请求和响应的header,body和元数据
     */
    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }

    @Bean
    public Encoder feignFormEncoder(){
        return new SpringFormEncoder();
    }
}
