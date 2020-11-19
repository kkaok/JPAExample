package com.example.jpa.emp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.emp.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findByLastName(String lastName);

	List<Employee> findByFirstName(String lastName);

}
