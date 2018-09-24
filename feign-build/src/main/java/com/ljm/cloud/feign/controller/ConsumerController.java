package com.ljm.cloud.feign.controller;

import com.ljm.cloud.feign.feign.UserFeignClient;
import com.ljm.cloud.feign.pojo.User;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lijunming
 * @date 2018/8/19 下午2:19
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class ConsumerController {

    private static  final Logger LOGGER= LoggerFactory.getLogger(ConsumerController.class);

    private UserFeignClient userFeignClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancedClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public ConsumerController(Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.userFeignClient= Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","19951224")).target(UserFeignClient.class,"http://user/");

        this.adminFeignClient= Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","19951224")).target(UserFeignClient.class,"http://user/");
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id){
         return this.userFeignClient.findById(id);
    }

    @GetMapping("/admin/{id}")
    public User findByIdAdmin(@PathVariable Integer id){
        return this.adminFeignClient.findById(id);
    }
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return this.discoveryClient.getInstances("user");
    }

    @GetMapping("/logUserInfo")
    public void logUserInfo(){
        ServiceInstance  serviceInstance=this.loadBalancedClient.choose("user");
        LOGGER.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }
}
