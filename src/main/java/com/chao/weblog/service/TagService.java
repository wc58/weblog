package com.chao.weblog.service;

import com.chao.weblog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag editOrSaveTag(Tag tag);

    void deleteTag(Integer id);

    Tag getTag(Integer id);

    Tag findTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

}
