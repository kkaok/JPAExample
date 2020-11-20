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

	public List<Employee> findByLastName(String lastName);

	public List<Employee> findByFirstName(String firstName);

	public List<Employee> findByFirstNameContaining(String firstName);
	
	public List<Employee> findByFirstNameContains(String firstName);
	
	public List<Employee> findByFirstNameIsContaining(String firstName);

	public List<Employee> findByFirstNameLike(String firstName);

	public List<Employee> findByFirstNameStartsWith(String firstName);
	
	public List<Employee> findByFirstNameEndsWith(String firstName);
	
	public List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
	
	public List<Employee> findByFirstNameNotContaining(String firstName);
	
	public List<Employee> findByFirstNameNotLike(String firstName);
	
    @Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.firstName LIKE %:firstName%")
    public List<Employee> searchByFirstNameLike(@Param("firstName") String firstName);
    
    @Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.firstName LIKE ?1%")
    public List<Employee> searchByFirstNameStartsWith(String firstName);
    
    //Escaping works in SpringBoot >= 2.4.1
    //@Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.director LIKE %?#{escape([0])} escape ?#{escapeCharacter()}")
    @Query("SELECT m FROM TBL_EMPLOYEES m WHERE m.firstName LIKE %:#{[0]}")
    public List<Employee> searchByFirstNameEndsWith(String firstName);

	public List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	public List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

	public List<Employee> findAll(Sort sort);

	public Page<Employee> findAll(Pageable pageable);

	public List<Employee> findByLastName(String lastName, Pageable pageable);

}
