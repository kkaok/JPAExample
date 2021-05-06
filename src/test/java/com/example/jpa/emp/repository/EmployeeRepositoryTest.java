package com.example.jpa.emp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;

//@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.example.jpa")  // 스캔 범위 지정
class EmployeeRepositoryTest {

	@Autowired 
	private EmployeeService employeeService;
	
	@Test
    @DisplayName("saveEmployee() : 저장 테스트")
	void saveEmployee() throws JsonProcessingException {
	    
        Dept devDept = new Dept("dev", "개발팀");
        //deptJPARepository.save(devDept);
        Employee emp1 = new Employee("Jack", "Bauer", "a1@a.a");
        devDept.addEmployee(emp1); 
        Employee employee = employeeService.save(emp1);
	  
        //log.info(employee.toString());
        
	    assertThat(employee.getEmail()).isSameAs(emp1.getEmail());
	}

}
