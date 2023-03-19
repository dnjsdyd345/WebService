package com.spring.blogservice.controller;

import com.spring.blogservice.entity.SearchBlog;
import com.spring.blogservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping("/")
    public String test(){
        String query ="test";
//        return mainService.test(query);

        return mainService.test2(query);
    }
}
