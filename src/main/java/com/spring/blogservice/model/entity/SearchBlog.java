package com.spring.blogservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchBlog {

    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private Date datetime;

}
