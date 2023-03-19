package com.spring.blogservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.Subquery;

@Getter
@Setter
public class SearchBlogReq {
    private String query;
    private String sort;
    private String page;
    private int size;
}
