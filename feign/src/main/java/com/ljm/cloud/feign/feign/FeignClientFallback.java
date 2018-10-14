package com.ljm.cloud.feign.feign;

import com.ljm.cloud.feign.pojo.User;
import org.springframework.stereotype.Component;

/**
 * 出现异常回退类
 * @author lijunming
 * @date 2018/9/23 下午5:23
 */
@Component
public class FeignClientFallback implements  UserFeignClient   {
    @Override
    public User findById(Integer id) {
        User user=new User();
        user.setId(-1);
        user.setName("默认用户");
        return user;
    }
}
