package com.spring.blogservice.repository.qrepository;

import com.spring.blogservice.model.entity.TbSearchBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QTbSearchBlogRepository {
    Page<TbSearchBlog> findAll(Pageable pageable);
}
