package com.example.jpa.emp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.emp.entity.Employee;

public interface EmployeeJPARepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByLastName(String lastName);

	public List<Employee> findByFirstName(String lastName);

	public List<Employee> findByFirstNameContaining(String firstName);
	
	public List<Employee> findByFirstNameContains(String firstName);
	
	public List<Employee> findByFirstNameIsContaining(String firstName);
	
}
