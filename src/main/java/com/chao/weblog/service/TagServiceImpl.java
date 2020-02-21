package com.chao.weblog.service;

import com.chao.weblog.dao.TagDao;
import com.chao.weblog.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    @Transactional
    @Override
    public Tag editOrSaveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Integer id) {
        tagDao.deleteById(id);
    }

    @Override
    public Tag getTag(Integer id) {
        return tagDao.getOne(id);
    }

    @Override
    public Tag findTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }
}
