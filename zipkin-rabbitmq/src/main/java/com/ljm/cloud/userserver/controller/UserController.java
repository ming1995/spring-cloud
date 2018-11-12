package com.ljm.cloud.userserver.controller;

import com.ljm.cloud.userserver.dao.UserRepository;
import com.ljm.cloud.userserver.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author lijunming
 * @date 2018/8/19 上午11:59
 */
@RestController
public class UserController {
    private static  final Logger LOGGER= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user=(UserDetails) principal;
            Collection<? extends GrantedAuthority> collection=user.getAuthorities();
            for(GrantedAuthority c:collection){
                LOGGER.info("当前用户是{},角色是{}",user.getUsername(),c.getAuthority());
            }

        }
        User user=this.userRepository.findOne(id);
        return user;
    }
}
