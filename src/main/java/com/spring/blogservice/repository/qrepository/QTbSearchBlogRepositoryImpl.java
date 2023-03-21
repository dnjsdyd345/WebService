package com.spring.blogservice.repository.qrepository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.blogservice.model.entity.QTbSearchBlog;
import com.spring.blogservice.model.entity.TbSearchBlog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class QTbSearchBlogRepositoryImpl implements QTbSearchBlogRepository{
    private final JPAQueryFactory queryFactory;

    public QTbSearchBlogRepositoryImpl(@Qualifier("jpaQueryFactory") JPAQueryFactory jpaQueryFactory){
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public Page<TbSearchBlog> findAll(Pageable pageable){
        Long size = queryFactory
                .select(QTbSearchBlog.tbSearchBlog.count())
                .from(QTbSearchBlog.tbSearchBlog)
                .orderBy(orderby(pageable))
                .fetchOne();
        List<TbSearchBlog> result = queryFactory
                .selectFrom(QTbSearchBlog.tbSearchBlog)
                .orderBy(orderby(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageable, size == null ? 0 : size);
    }

    private OrderSpecifier<?>[] orderby(Pageable pageable){
        if(pageable.getSort().isEmpty()) return null;
        return pageable.getSort().stream()
                .map(order -> {
                    Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                    Path<?> path = Expressions.path(TbSearchBlog.class, QTbSearchBlog.tbSearchBlog, order.getProperty());
                    return new OrderSpecifier(direction, path);
                })
                .toArray(OrderSpecifier<?>[]::new);
    }

}
