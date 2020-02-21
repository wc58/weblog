package com.chao.weblog.service;

import com.chao.weblog.dao.TypeDao;
import com.chao.weblog.exception.NotFoundException;
import com.chao.weblog.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Type editOrSaveType(Type type) {
        return typeDao.save(type);
    }

    @Override
    public Type getType(Integer id) {
        return typeDao.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }


    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Transactional
    @Override
    public void deleteType(Integer id) {
        typeDao.deleteById(id);
    }
}
