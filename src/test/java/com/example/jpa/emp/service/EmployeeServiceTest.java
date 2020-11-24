package com.example.jpa.emp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.jpa.emp.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class EmployeeServiceTest {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class);

	@Autowired 
	private EmployeeService employeeService;

	@Test
	void test() throws JsonProcessingException {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Jack", "Bauer", "a1@a.a"));
		employees.add(new Employee("Chloe", "O'Brian", "a2@a.a"));
		employees.add(new Employee("Kim", "Bauer", "a3@a.a"));
		employees.add(new Employee("David", "Palmer", "a4@a.a"));
		employees.add(new Employee("Michelle", "Dessler", "kkaok@a.a"));
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

		handleList("findByFirstNameAndLastName(\"Kim\", \"Bauer\")", employeeService.findByFirstNameAndLastName("Kim", "Bauer"));

		handleList("findByFirstNameOrLastName(\"Kim\", \"Bauer\")", employeeService.findByFirstNameOrLastName("Kim", "Bauer"));

		Sort sort = Sort.by("firstName").ascending()
				  .and(Sort.by("lastName").descending());

		handleList("findAll(Sort sort)", employeeService.findAll(sort));

		Pageable pageable = 
				PageRequest.of(0, 3, Sort.by("lastName").descending().and(Sort.by("firstName")));
		
		handleList("findByLastName(\"Bauer\", pageable)", employeeService.findByLastName("Bauer", pageable));

		Pageable pageableAll = 
				PageRequest.of(0, 3, Sort.by("lastName").descending().and(Sort.by("firstName")));
		Page<Employee> pageResult = employeeService.findAll(pageableAll);

		handleList("findAll(pageableAll)", pageResult.getContent());
		
		
		Employee emp = employeeService.findOneByEmail("kkaok@a.a");
		System.out.println(emp.toString());

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
