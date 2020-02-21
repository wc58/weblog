package com.chao.weblog.service;

import com.chao.weblog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    Type editOrSaveType(Type type);

    Type getType(Integer id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);


    void deleteType(Integer id);

}
