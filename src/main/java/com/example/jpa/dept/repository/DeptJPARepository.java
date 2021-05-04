package com.example.jpa.dept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.dept.entity.Dept;

public interface DeptJPARepository extends JpaRepository<Dept, Long> {

}
