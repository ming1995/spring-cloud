package com.ljm.cloud.withouteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WithoutEurekaApplication {
    //等价于直接 RestTemplate restTemplate=new RestTemplate();
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(WithoutEurekaApplication.class, args);
    }
}
