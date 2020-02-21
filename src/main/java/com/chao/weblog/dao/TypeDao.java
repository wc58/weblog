package com.chao.weblog.dao;

import com.chao.weblog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeDao extends JpaRepository<Type,Integer> {

    Type findByName(String name);

}
