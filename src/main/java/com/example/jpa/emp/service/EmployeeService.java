package com.example.jpa.emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.addr.repository.AddrJPARepository;
import com.example.jpa.dept.repository.DeptJPARepository;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.repository.EmployeeJPARepository;
import com.example.jpa.emp.repository.EmployeeRepositorySupport;
import com.example.jpa.exception.DataNotFoundException;

@Service
public class EmployeeService {

	private EmployeeJPARepository employeeJPARepository;
	
	private EmployeeRepositorySupport employeeRepositorySupport;
	
    private DeptJPARepository deptJPARepository;

    private AddrJPARepository addrJPARepository;

    EmployeeService(@Autowired EmployeeJPARepository employeeJPARepository
            , @Autowired EmployeeRepositorySupport employeeRepositorySupport
            , @Autowired AddrJPARepository addrJPARepository
            , @Autowired DeptJPARepository deptJPARepository){
        this.employeeJPARepository = employeeJPARepository;
        this.employeeRepositorySupport = employeeRepositorySupport;
        this.addrJPARepository = addrJPARepository;
        this.deptJPARepository = deptJPARepository;
    }

    @Transactional(readOnly = true)
	public List<Employee> findAll(){
		return employeeJPARepository.findAll();
	}
	
    @Transactional
	public void saveAll(List<Employee>  employees) {
	    employeeJPARepository.saveAll(employees);
	}
	
	@Transactional
	public Employee save(Employee  employee) {
        deptJPARepository.save(employee.getDept());
        addrJPARepository.save(employee.getAddress());
	    Employee rEmployee = employeeJPARepository.save(employee);
	    return rEmployee;
	}
	
	//@Transactional(readOnly = true)
	public Employee findById(Long id) {
        Optional<Employee> result = employeeJPARepository.findById(id);
        return result.orElseThrow(() -> new DataNotFoundException("직원 정보가 존재하지 않습니다."));
	}

	public List<Employee> findByLastName(String lastName) {
		return employeeJPARepository.findByLastName(lastName);
	}

	public List<Employee> findByFirstName(String firstName) {
		return employeeJPARepository.findByFirstName(firstName);
	}

	// Containing, Contains, IsContaining and Like
	public List<Employee> findByFirstNameContaining(String firstName){
		return employeeJPARepository.findByFirstNameContaining(firstName);
	}
	
	public List<Employee> findByFirstNameContains(String firstName){
		return employeeJPARepository.findByFirstNameContains(firstName);
	}
	
	public List<Employee> findByFirstNameIsContaining(String firstName){
		return employeeJPARepository.findByFirstNameIsContaining(firstName);
	}

	public List<Employee> findByFirstNameLike(String firstName){
		return employeeJPARepository.findByFirstNameLike(firstName);
	}

	public List<Employee> findByFirstNameStartsWith(String firstName){
		return employeeJPARepository.findByFirstNameStartsWith(firstName);
	}
	
	public List<Employee> findByFirstNameEndsWith(String firstName){
		return employeeJPARepository.findByFirstNameEndsWith(firstName);
	}
	
	@Transactional(readOnly=true)
	public List<Employee> findByFirstNameContainingIgnoreCase(String firstName){
		return employeeJPARepository.findByFirstNameContainingIgnoreCase(firstName);
	}
	
	public List<Employee> findByFirstNameNotContaining(String firstName){
		return employeeJPARepository.findByFirstNameNotContaining(firstName);
	}
	
	public List<Employee> findByFirstNameNotLike(String firstName){
		return employeeJPARepository.findByFirstNameNotLike(firstName);
	}
	
    public List<Employee> searchByFirstNameLike(@Param("title") String firstName){
		return employeeJPARepository.searchByFirstNameLike(firstName);
	}
    
    public List<Employee> searchByFirstNameStartsWith(String firstName){
		return employeeJPARepository.searchByFirstNameStartsWith(firstName);
	}
    
    public List<Employee> searchByFirstNameEndsWith(String firstName){
		return employeeJPARepository.searchByFirstNameEndsWith(firstName);
	}

    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName){
    	return employeeJPARepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Employee> findByFirstNameOrLastName(String firstName, String lastName){
    	return employeeJPARepository.findByFirstNameOrLastName(firstName, lastName);
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
