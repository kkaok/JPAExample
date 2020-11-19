package com.example.jpa.emp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;

import com.example.jpa.emp.entity.Employee;

@SpringBootTest
class EmployeeServiceTest {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class);

	@Autowired 
	private EmployeeService employeeService;

	@Test
	void test() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Jack", "Bauer"));
		employees.add(new Employee("Chloe", "O'Brian"));
		employees.add(new Employee("Kim", "Bauer"));
		employees.add(new Employee("David", "Palmer"));
		employees.add(new Employee("Michelle", "Dessler"));
		employeeService.saveAll(employees);


		// fetch an individual customer by ID
		Employee employee = employeeService.findById(1L);
		log.info("--------------------------------");
		log.info("Employee found with findById(1L):");
		log.info("--------------------------------");
		log.info(employee.toString());
		log.info("");

		handleList("findAll", employeeService.findAll());

		handleList("findByLastName(\"Bauer\")", employeeService.findByLastName("Bauer"));

		handleList("findByFirstName('Kim')", employeeService.findByFirstName("Kim"));

		handleList("findByfisrtNameContaining('ak')", employeeService.findByFirstNameContaining("ck"));
		
		handleList("findByFirstNameLike('ak')", employeeService.findByFirstNameLike("ck"));
		
		handleList("findByFirstNameStartsWith('Ch')", employeeService.findByFirstNameStartsWith("Ch"));
		
		handleList("findByFirstNameEndsWith('oe')", employeeService.findByFirstNameEndsWith("oe"));
		
		handleList("findByFirstNameContainingIgnoreCase('kim')", employeeService.findByFirstNameContainingIgnoreCase("kim"));
		
		handleList("findByFirstNameNotContaining('avid')", employeeService.findByFirstNameNotContaining("avid"));
		
		handleList("findByFirstNameNotLike('Kim')", employeeService.findByFirstNameNotLike("Kim"));
		
		handleList("searchByFirstNameLike('im')", employeeService.searchByFirstNameLike("im"));
		
		handleList("searchByFirstNameStartsWith('Kim')", employeeService.searchByFirstNameStartsWith("Kim"));
		
		handleList("searchByFirstNameEndsWith('id')", employeeService.searchByFirstNameEndsWith("id"));

		log.info("");
	}
	
	private <T> void handleList(String title, List<T> list) {
		log.info("--------------------------------------------");
		log.info("Employee found with "+title+":");
		log.info("--------------------------------------------");
		list.forEach(bauer -> {
			log.info(bauer.toString());
		});
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
	
}
