package com.example.jpa.emp.repository;

import static com.example.jpa.emp.entity.QEmployee.employee;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.example.jpa.emp.entity.Employee;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class EmployeeRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public EmployeeRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Employee.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Employee findOneByEmail(String email) {
        return jpaQueryFactory
                .selectFrom(employee)
                .where(employee.email.eq(email))
                .fetchOne();
    }

}
