package com.spring.blogservice.service;

import com.spring.blogservice.entity.SearchBlog;
import com.spring.blogservice.entity.SearchBlogReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MainService {

    @Value("${RestApi.kakao.host}")
    String host;
    @Value("${RestApi.kakao.path}")
    String path;
    @Value("${RestApi.kakao.key}")
    String key;

    public SearchBlog test(String query){
            //http://localhost/api/server/user/{userId}/name/{userName}

            URI uri = UriComponentsBuilder
                    .fromUriString(host)
                    .path(path)
                    .encode()
                    .build()
                    .expand("100","ila") //순차적으로 파라미터넣기
                    .toUri();

            System.out.println(uri);

            // http body -> object -> object mapper -> json -> rest template -> http body json
            // http body를 만들건데 object만 보낼거야 object mapper가 json을만들어서 rest template에 보내서 http body에 json으로 넣어줄것이다.

            SearchBlogReq req = new SearchBlogReq();
            req.setQuery(query);

            RequestEntity<SearchBlogReq> requestEntity = RequestEntity
                    .post(host)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", key)
                    .body(req);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<SearchBlog> response = restTemplate.exchange(requestEntity, SearchBlog.class);
            return response.getBody();

        }


}
