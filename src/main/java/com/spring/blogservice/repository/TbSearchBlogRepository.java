package com.spring.blogservice.repository;

import com.spring.blogservice.model.entity.TbSearchBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TbSearchBlogRepository extends JpaRepository<TbSearchBlog, String> {
    List<TbSearchBlog> findAll();
    TbSearchBlog save(TbSearchBlog tbSearchBlog);
    Optional<TbSearchBlog> findById(Long id);
}
