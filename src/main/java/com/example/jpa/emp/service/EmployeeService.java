package com.example.jpa.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.repository.EmployeeJPARepository;
import com.example.jpa.emp.repository.EmployeeRepository;
import com.example.jpa.emp.repository.EmployeeRepositorySupport;
import com.example.jpa.exception.DataNotFoundException;

@Service
public class EmployeeService {

	@Autowired 
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private EmployeeJPARepository employeeJPARepository;
	
	@Autowired 
	private EmployeeRepositorySupport employeeRepositorySupport;
	
	public List<Employee> findAll(){
		return handleList(employeeRepository.findAll());
	}
	
	public void saveAll(List<Employee>  employees) {
		employeeRepository.saveAll(employees);
	}
	
	public Employee findById(Long id) {
		Optional<Employee> result = employeeRepository.findById(1L);
		return result.orElseThrow(() -> new DataNotFoundException("직원 정보가 존재하지 않습니다."));
//		return result.orElse(null);
	}

	public List<Employee> findByLastName(String lastName) {
		return handleList(employeeRepository.findByLastName(lastName));
	}

	public List<Employee> findByFirstName(String firstName) {
		return handleList(employeeRepository.findByFirstName(firstName));
	}
	
	public <T> List<T> handleList(Iterable<T> iterable) {
		List<T> result = new ArrayList<>();
		iterable.forEach(result::add);
		return result;
	}
	
	// Containing, Contains, IsContaining and Like
	public List<Employee> findByFirstNameContaining(String firstName){
		return handleList(employeeRepository.findByFirstNameContaining(firstName));
	}
	
	public List<Employee> findByFirstNameContains(String firstName){
		return handleList(employeeRepository.findByFirstNameContains(firstName));
	}
	
	public List<Employee> findByFirstNameIsContaining(String firstName){
		return handleList(employeeRepository.findByFirstNameIsContaining(firstName));
	}

	public List<Employee> findByFirstNameLike(String firstName){
		return handleList(employeeRepository.findByFirstNameLike(firstName));
	}

	public List<Employee> findByFirstNameStartsWith(String firstName){
		return handleList(employeeRepository.findByFirstNameStartsWith(firstName));
	}
	
	public List<Employee> findByFirstNameEndsWith(String firstName){
		return handleList(employeeRepository.findByFirstNameEndsWith(firstName));
	}
	
	public List<Employee> findByFirstNameContainingIgnoreCase(String firstName){
		return handleList(employeeRepository.findByFirstNameContainingIgnoreCase(firstName));
	}
	
	public List<Employee> findByFirstNameNotContaining(String firstName){
		return handleList(employeeRepository.findByFirstNameNotContaining(firstName));
	}
	
	public List<Employee> findByFirstNameNotLike(String firstName){
		return handleList(employeeRepository.findByFirstNameNotLike(firstName));
	}
	
    public List<Employee> searchByFirstNameLike(@Param("title") String firstName){
		return handleList(employeeRepository.searchByFirstNameLike(firstName));
	}
    
    public List<Employee> searchByFirstNameStartsWith(String firstName){
		return handleList(employeeRepository.searchByFirstNameStartsWith(firstName));
	}
    
    public List<Employee> searchByFirstNameEndsWith(String firstName){
		return handleList(employeeRepository.searchByFirstNameEndsWith(firstName));
	}

    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName){
    	return handleList(employeeRepository.findByFirstNameAndLastName(firstName, lastName));
    }

    public List<Employee> findByFirstNameOrLastName(String firstName, String lastName){
    	return handleList(employeeRepository.findByFirstNameOrLastName(firstName, lastName));
    }
    
    public List<Employee> findAll(Sort sort){
    	return employeeJPARepository.findAll(sort);
    }

    public Page<Employee> findAll(Pageable pageable){
    	return employeeJPARepository.findAll(pageable);
    }

    public List<Employee> findByLastName(String lastName, Pageable pageable){
    	return employeeJPARepository.findByLastName(lastName, pageable);
    }

    public Employee findOneByEmail(String email){
        return employeeRepositorySupport.findOneByEmail(email);
    }
	
}
