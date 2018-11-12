package com.ljm.cloud.userserver.dao;

import com.ljm.cloud.userserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lijunming
 * @date 2018/8/19 上午11:57
 */
public interface UserRepository extends JpaRepository<User,Integer> {

}
