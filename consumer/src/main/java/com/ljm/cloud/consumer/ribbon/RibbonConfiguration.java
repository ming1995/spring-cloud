package com.ljm.cloud.consumer.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类为Ribbin的配置类
 * 自定义会覆盖默认配置
 * 注意：该类不应该在主应用程序上下文的@ComponentScan所扫描的包中
 * @author lijunming
 * @date 2018/9/19 下午11:02
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule(){
        //自定义负载均衡规则，改为随机
        return new RandomRule();
    }

}
