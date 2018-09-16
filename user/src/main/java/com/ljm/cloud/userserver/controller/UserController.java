package com.ljm.cloud.userserver.controller;

import com.ljm.cloud.userserver.dao.UserRepository;
import com.ljm.cloud.userserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijunming
 * @date 2018/8/19 上午11:59
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id){
        User user=this.userRepository.findOne(id);
        return user;
    }
}
