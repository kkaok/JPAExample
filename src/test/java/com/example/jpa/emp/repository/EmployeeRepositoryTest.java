package com.example.jpa.emp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.dept.repository.DeptJPARepository;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.repository.EmployeeJPARepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired 
	private EmployeeJPARepository employeeJPARepository;
	
    @Autowired 
    private DeptJPARepository deptJPARepository;

	@Test
	void saveEmployee2() throws JsonProcessingException {
	    
        Dept devDept = new Dept("dev", "개발팀");
        deptJPARepository.save(devDept);
        Employee emp1 = new Employee("Jack", "Bauer", "a1@a.a");
        devDept.addEmployee(emp1); 
        Employee employee = employeeJPARepository.save(emp1);
	    
	    assertThat(employee.getEmail()).isSameAs(emp1.getEmail());
	}

}
