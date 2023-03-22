package com.spring.blogservice.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Schema(name = "TbSearchBlog", title = "TbSearchBlog", description = "BLOG 검색 Request Entity")
public class TbSearchBlog {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Schema(title = "ID")
    private long id;

    @Schema(title = "검색어")
    private String query;

    @Schema(title = "등록시간")
    private String regDtti;

    @Schema(title = "조회 수 ")
    private long count;

}
