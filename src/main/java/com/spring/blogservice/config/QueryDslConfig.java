package java.com.spring.blogservice.config;

import com.sun.org.slf4j.internal.LoggerFactory;

@Configuration
public class QueryDslConfig {
    private final Logger logger = LoggerFactory.getLogger(QueryDslConfig.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){ return new JPAQueryFactory(entityManager); }
}
