package com.chao.weblog.service;

import com.chao.weblog.pojo.User;

public interface AdminService {

    User checkUser(String username,String password);

}
