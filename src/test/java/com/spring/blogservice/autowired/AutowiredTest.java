package com.spring.blogservice.autowired;

import com.spring.blogservice.model.entity.TbSearchBlog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(TbSearchBlog tbSearchBlog){
            System.out.println("noBean1 = " + tbSearchBlog);
        }

        @Autowired
        public void setNoBean2(@Nullable TbSearchBlog tbSearchBlog){
            System.out.println("noBean2 = " + tbSearchBlog);
        }

        @Autowired
        public void setNoBean3(Optional<TbSearchBlog> tbSearchBlog) {
            System.out.println("noBean3 = " + tbSearchBlog);
        }
    }
}
