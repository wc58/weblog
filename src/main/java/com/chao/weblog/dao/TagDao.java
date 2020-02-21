package com.chao.weblog.dao;

import com.chao.weblog.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag,Integer> {

    Tag findByName(String name);

}
