package com.example.jpa.emp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpa.emp.entity.Employee;

public interface EmployeeJPARepository extends JpaRepository<Employee, Long> {

	List<Employee> findByLastName(String lastName);

	List<Employee> findByFirstName(String firstName);

	List<Employee> findByFirstNameContaining(String firstName);
	
	List<Employee> findByFirstNameContains(String firstName);
	
	List<Employee> findByFirstNameIsContaining(String firstName);

	List<Employee> findByFirstNameLike(String firstName);

	List<Employee> findByFirstNameStartsWith(String firstName);
	
	List<Employee> findByFirstNameEndsWith(String firstName);
	
	List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
	
	List<Employee> findByFirstNameNotContaining(String firstName);
	
	List<Employee> findByFirstNameNotLike(String firstName);
	
    @Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.firstName LIKE %:firstName%")
    List<Employee> searchByFirstNameLike(@Param("firstName") String firstName);
    
    @Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.firstName LIKE ?1%")
    List<Employee> searchByFirstNameStartsWith(String firstName);
    
    //Escaping works in SpringBoot >= 2.4.1
    //@Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.director LIKE %?#{escape([0])} escape ?#{escapeCharacter()}")
    @Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.firstName LIKE %:#{[0]}")
    List<Employee> searchByFirstNameEndsWith(String firstName);

	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

	List<Employee> findAll(Sort sort);

	Page<Employee> findAll(Pageable pageable);

	List<Employee> findByLastName(String lastName, Pageable pageable);

}
