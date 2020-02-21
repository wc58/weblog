package com.chao.weblog.dao;

import com.chao.weblog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminDao extends JpaRepository<User,Integer> {

    User findByUserNameAndPassword(String username,String password);

}
