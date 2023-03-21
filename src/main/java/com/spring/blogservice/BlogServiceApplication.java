package com.spring.blogservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import javax.management.MXBean;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class BlogServiceApplication {

//    private final Logger logger = LoggerFactory.getLogger((this.getClass()));

    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }

    @Bean
    public ObjectMapper mapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper;
    }

    @PostConstruct
    public void started() { TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul")); }

}
