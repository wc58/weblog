package com.chao.weblog.service;

import com.chao.weblog.dao.AdminDao;
import com.chao.weblog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao dao;

    @Override
    public User checkUser(String username, String password) {
        User user = dao.findByUserNameAndPassword(username, password);
        return user;
    }
}
