package com.spring.blogservice.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.blogservice.model.dto.searchBlogDto;
import com.spring.blogservice.model.entity.TbSearchBlog;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MainService {

    @Value("${RestApi.kakao.host}")
    String host;
    @Value("${RestApi.kakao.path}")
    String path;
    @Value("${RestApi.kakao.key}")
    String key;

    @Transactional(readOnly = true)
    public searchBlogDto.SEARCH_RESPONSE getSearchBlog(searchBlogDto.SEARCH_CONDITION searchReq) throws DataAccessException {

        // set Header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", key);
        // set request Entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        String url = host + path + "?query="+ searchReq.getQuery() +
                "&page=" + searchReq.getPage() +
                "&size=" + searchReq.getSize() +
                "&sort=" + searchReq.getSort();

        TbSearchBlog tbSearchBlog = new TbSearchBlog();


        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, searchBlogDto.SEARCH_RESPONSE.class).getBody();
    }


}
