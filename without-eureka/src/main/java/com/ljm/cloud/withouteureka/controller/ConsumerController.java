package com.ljm.cloud.withouteureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author lijunming
 * @date 2018/8/19 下午2:19
 */
@RestController
public class ConsumerController {

    private static  final Logger LOGGER= LoggerFactory.getLogger(ConsumerController.class);


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancedClient;

    @Autowired
    private DiscoveryClient discoveryClient;



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
