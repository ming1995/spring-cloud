package com.ljm.cloud.feign.controller;

import com.ljm.cloud.feign.feign.UserFeignClient;
import com.ljm.cloud.feign.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    private UserFeignClient userFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancedClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     *timeoutInMilliseconds     默认1000毫秒，超过就执行回退方法
     * timeInMilliseconds滚动窗口 默认为10000秒
     * coreSize 执行线程个数      默认为10
     * maxQueueSize 最大线程个数  默认10，一般和coreSize一样大小
     * ignoreExceptions         如果不想进回退方法 ,指定不想回滚的异常
     */
//    @HystrixCommand(fallbackMethod = "findByIdFallback",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
//            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value = "10000")},
//            threadPoolProperties = {
//            @HystrixProperty(name="coreSize",value = "1"),
//            @HystrixProperty(name = "maxQueueSize",value = "10")
//            }//,ignoreExceptions = {RuntimeException.class}
//    )
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id){
         return this.userFeignClient.findById(id);
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

    /**
     * 当调用的服务挂掉之后会调用的方法
     * @return 返回默认值
     */
    public  User findByIdFallback(Integer id,Throwable tb){
            LOGGER.info("进入回退方法，异常:",tb);
            User user=new User();
            user.setId(-1);
            user.setName("默认用户");
            return user;
    }

    /**
     * 调用非JVM服务
     */
    @GetMapping("/test")
    public String test(){
        return this.restTemplate.getForObject("http://sidecar/",String.class);
    }
}
