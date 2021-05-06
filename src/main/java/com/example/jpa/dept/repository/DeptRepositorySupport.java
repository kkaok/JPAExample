package com.example.jpa.dept.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.example.jpa.dept.entity.Dept;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptRepositorySupport extends QuerydslRepositorySupport {

    final JPAQueryFactory jpaQueryFactory;

    public DeptRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Dept.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
