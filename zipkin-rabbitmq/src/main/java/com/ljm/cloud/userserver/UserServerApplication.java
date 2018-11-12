package com.ljm.cloud.userserver;

import com.netflix.discovery.DiscoveryClient;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

    /**
     * 实现服务注册自定义过滤，还有1种是写死在配置文件中 http://ming:19951224@localhost:8761/eureka/
     * @return
     */
    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs  discoveryClientOptionalArgs(){
        DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs=new DiscoveryClient.DiscoveryClientOptionalArgs();
        List<ClientFilter> additionalFilters=new ArrayList<>();
        additionalFilters.add(new HTTPBasicAuthFilter("ming","19951224"));
        discoveryClientOptionalArgs.setAdditionalFilters(additionalFilters);
        return discoveryClientOptionalArgs;
    }
}
