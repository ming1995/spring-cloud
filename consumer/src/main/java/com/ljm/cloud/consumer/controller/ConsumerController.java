package com.ljm.cloud.consumer.controller;

import com.ljm.cloud.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Value("${user.server.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id){
        return this.restTemplate.getForObject(url+"/user/"+id,User.class);
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return this.discoveryClient.getInstances("user");
    }
}
