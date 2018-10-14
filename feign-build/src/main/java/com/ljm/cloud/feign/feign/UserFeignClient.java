package com.ljm.cloud.feign.feign;
import com.ljm.cloud.feign.pojo.User;
import feign.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lijunming
 * @date 2018/9/22 下午12:08
 */
public interface UserFeignClient {


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Integer id);
}
