package com.spring.blogservice.service;

import com.spring.blogservice.controller.response.ResponseBuilder;
import com.spring.blogservice.error.BusinessException;
import com.spring.blogservice.error.ErrorCode;
import com.spring.blogservice.model.dto.searchBlogDto;
import com.spring.blogservice.model.entity.TbSearchBlog;
import com.spring.blogservice.repository.TbSearchBlogRepository;
import net.bytebuddy.build.BuildLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.hibernate.internal.CoreLogging.logger;

@Service
public class MainService {

    @Value("${RestApi.kakao.host}")
    String host;
    @Value("${RestApi.kakao.path}")
    String path;
    @Value("${RestApi.kakao.key}")
    String key;

    private final TbSearchBlogRepository tbSearchBlogRepository;

    @Autowired
    public MainService(TbSearchBlogRepository tbSearchBlogRepository) {
        this.tbSearchBlogRepository = tbSearchBlogRepository;
    }

    @Transactional(readOnly = true)
    public searchBlogDto.SEARCH_RESPONSE getSearchBlog(searchBlogDto.SEARCH_CONDITION searchReq) throws DataAccessException {

        if(searchReq == null ||searchReq.getQuery() == null){
            if(searchReq.getQuery() == null){
                throw new BusinessException(ErrorCode.NO_CONTENT);
            }
        }

        // set Header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", key);
        // set request Entity
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        String url = host + path + "?query="+ searchReq.getQuery() +
                "&page=" + searchReq.getPage() +
                "&size=" + searchReq.getSize() +
                "&sort=" + searchReq.getSort();


        Date date = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", currentLocale);

        TbSearchBlog tbSearchBlog = new TbSearchBlog();
        TbSearchBlog check = tbSearchBlogRepository.findByQuery(searchReq.getQuery()).orElse(null);
        int count = 1;
        if(check != null){
            count = (int) check.getCount() + 1;
            tbSearchBlog.setId(check.getId());
        }

        tbSearchBlog.setQuery(searchReq.getQuery());
        tbSearchBlog.setCount(count);
        tbSearchBlog.setRegDtti(String.valueOf(formatter.format(date)));
        tbSearchBlogRepository.saveAndFlush(tbSearchBlog);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, searchBlogDto.SEARCH_RESPONSE.class).getBody();
    }

    @Transactional(readOnly = true)
    public Page<TbSearchBlog> findAll(PageRequest pageRequest) throws DataAccessException {
        return tbSearchBlogRepository.findAll(pageRequest);
    }

    @Transactional
    public TbSearchBlog bindById(Long pk) throws DataAccessException {
        final Optional<TbSearchBlog> byId = tbSearchBlogRepository.findById(pk);
        return byId.orElse(null);
    }


    @Transactional
    public void deleteByid(TbSearchBlog tbSearchBlog) throws DataAccessException {
        tbSearchBlogRepository.delete(tbSearchBlog);
    }

}
