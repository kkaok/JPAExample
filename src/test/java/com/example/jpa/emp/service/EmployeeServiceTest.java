package com.example.jpa.emp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.dept.service.DeptService;
import com.example.jpa.emp.entity.EmpAPI;
import com.example.jpa.emp.entity.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest 
@ComponentScan("com.example.jpa")  // 스캔 범위 지정
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceTest {

	@Autowired 
	private EmployeeService employeeService;

	@Autowired 
	private DeptService deptService;

    @Autowired 
    private ModelMapper modelMapper;

	@Test
	void test() throws JsonProcessingException {
        
        Dept devDept = new Dept("dev", "개발팀");

        deptService.save(devDept);

        Employee emp1 = new Employee("Jack", "Bauer", "a1@a.a");
        devDept.addEmployee(emp1); 
        employeeService.save(emp1);

        Employee emp2 = new Employee("Chloe", "Chloe", "a2@a.a");
        devDept.addEmployee(emp2); 
        employeeService.save(emp2);
        
        handleList("findAll", employeeService.findAll());
        
        handleList("findAllDept", deptService.findAll());

        //if(1>0) return;
		// fetch an individual customer by ID
        log.info("한건 테스트 시작 ");
		Employee employee = employeeService.findById(emp1.getId());
		Employee employee5 = employeeService.findById(emp2.getId());
		log.info("--------------------------------");
		log.info("Employee found with findById(4L):");
		log.info("--------------------------------");
		
		EmpAPI api = modelMapper.map(employee, EmpAPI.class);
		log.info(api.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        String jsonString = objectMapper.writeValueAsString(api);
        log.info(jsonString);
        log.info(employee.toString());
		log.info("한건 테스트 종료 ");

//        handleList("#####################findDeptAll", deptService.findAll());

//        Employee emp = employeeService.findOneByEmail("kkaok@a.a");
//        System.out.println(emp.toString());

		//
//
//		handleList("findByLastName(\"Bauer\")", employeeService.findByLastName("Bauer"));
//
//		handleList("findByFirstName('Kim')", employeeService.findByFirstName("Kim"));
//
//		handleList("findByfisrtNameContaining('ak')", employeeService.findByFirstNameContaining("ck"));
//		
//		handleList("findByFirstNameLike('ak')", employeeService.findByFirstNameLike("ck"));
//		
//		handleList("findByFirstNameStartsWith('Ch')", employeeService.findByFirstNameStartsWith("Ch"));
//		
//		handleList("findByFirstNameEndsWith('oe')", employeeService.findByFirstNameEndsWith("oe"));
//		
//		handleList("findByFirstNameContainingIgnoreCase('kim')", employeeService.findByFirstNameContainingIgnoreCase("kim"));
//		
//		handleList("findByFirstNameNotContaining('avid')", employeeService.findByFirstNameNotContaining("avid"));
//		
//		handleList("findByFirstNameNotLike('Kim')", employeeService.findByFirstNameNotLike("Kim"));
//		
//		handleList("searchByFirstNameLike('im')", employeeService.searchByFirstNameLike("im"));
//		
//		handleList("searchByFirstNameStartsWith('Kim')", employeeService.searchByFirstNameStartsWith("Kim"));
//		
//		handleList("searchByFirstNameEndsWith('id')", employeeService.searchByFirstNameEndsWith("id"));
//
//		handleList("findByFirstNameAndLastName(\"Kim\", \"Bauer\")", employeeService.findByFirstNameAndLastName("Kim", "Bauer"));
//
//		handleList("findByFirstNameOrLastName(\"Kim\", \"Bauer\")", employeeService.findByFirstNameOrLastName("Kim", "Bauer"));
//
//		Sort sort = Sort.by("firstName").ascending()
//				  .and(Sort.by("lastName").descending());
//
//		handleList("findAll(Sort sort)", employeeService.findAll(sort));
//
//		Pageable pageable = 
//				PageRequest.of(0, 3, Sort.by("lastName").descending().and(Sort.by("firstName")));
//		
//		handleList("findByLastName(\"Bauer\", pageable)", employeeService.findByLastName("Bauer", pageable));
//
//		Pageable pageableAll = 
//				PageRequest.of(0, 3, Sort.by("lastName").descending().and(Sort.by("firstName")));
//		Page<Employee> pageResult = employeeService.findAll(pageableAll);
//
//		handleList("findAll(pageableAll)", pageResult.getContent());
//		
//		
//		Employee emp = employeeService.findOneByEmail("kkaok@a.a");
//		System.out.println(emp.toString());
//
//		log.info("");
	}
	//@Test
	//@Transactional
	void ___test() throws JsonProcessingException {
	    
	    List<Dept> depts = new ArrayList<>();
	    depts.add(new Dept("dev", "개발팀"));
	    depts.add(new Dept("hr", "인사팀"));
	    depts.add(new Dept("sales", "영업팀"));
	    
	    deptService.saveAll(depts);
	    
	    handleList("findDeptAll", deptService.findAll());
	    
	    List<Employee> employees = new ArrayList<>();
	    employees.add(new Employee(1L, "Jack", "Bauer", "a1@a.a"));
	    employees.add(new Employee(1L,"Chloe", "O'Brian", "a2@a.a"));
	    employees.add(new Employee(2L,"Kim", "Bauer", "a3@a.a"));
	    employees.add(new Employee(3L,"David", "Palmer", "a4@a.a"));
	    employees.add(new Employee(3L,"Michelle", "Dessler", "kkaok@a.a"));
	    employeeService.saveAll(employees);
	    
	    handleList("findAll", employeeService.findAll());
	    
	    //if(1>0) return;
	    // fetch an individual customer by ID
	    log.info("한건 테스트 시작 ");
	    Employee employee = employeeService.findById(4L);
	    Employee employee5 = employeeService.findById(4L);
	    Employee employee6 = employeeService.findById(4L);
	    log.info("--------------------------------");
	    log.info("Employee found with findById(4L):");
	    log.info("--------------------------------");
	    
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration()
	    .setFieldMatchingEnabled(true)
	    .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
	    EmpAPI api = modelMapper.map(employee, EmpAPI.class);
	    log.info(api.toString());
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    
	    String jsonString = objectMapper.writeValueAsString(api);
	    log.info(jsonString);
	    log.info(employee.toString());
	    log.info("한건 테스트 종료 ");
	    
	    handleList("#####################findDeptAll", deptService.findAll());
	    
//        Employee emp = employeeService.findOneByEmail("kkaok@a.a");
//        System.out.println(emp.toString());
	    
	    //
//
//		handleList("findByLastName(\"Bauer\")", employeeService.findByLastName("Bauer"));
//
//		handleList("findByFirstName('Kim')", employeeService.findByFirstName("Kim"));
//
//		handleList("findByfisrtNameContaining('ak')", employeeService.findByFirstNameContaining("ck"));
//		
//		handleList("findByFirstNameLike('ak')", employeeService.findByFirstNameLike("ck"));
//		
//		handleList("findByFirstNameStartsWith('Ch')", employeeService.findByFirstNameStartsWith("Ch"));
//		
//		handleList("findByFirstNameEndsWith('oe')", employeeService.findByFirstNameEndsWith("oe"));
//		
//		handleList("findByFirstNameContainingIgnoreCase('kim')", employeeService.findByFirstNameContainingIgnoreCase("kim"));
//		
//		handleList("findByFirstNameNotContaining('avid')", employeeService.findByFirstNameNotContaining("avid"));
//		
//		handleList("findByFirstNameNotLike('Kim')", employeeService.findByFirstNameNotLike("Kim"));
//		
//		handleList("searchByFirstNameLike('im')", employeeService.searchByFirstNameLike("im"));
//		
//		handleList("searchByFirstNameStartsWith('Kim')", employeeService.searchByFirstNameStartsWith("Kim"));
//		
//		handleList("searchByFirstNameEndsWith('id')", employeeService.searchByFirstNameEndsWith("id"));
//
//		handleList("findByFirstNameAndLastName(\"Kim\", \"Bauer\")", employeeService.findByFirstNameAndLastName("Kim", "Bauer"));
//
//		handleList("findByFirstNameOrLastName(\"Kim\", \"Bauer\")", employeeService.findByFirstNameOrLastName("Kim", "Bauer"));
//
//		Sort sort = Sort.by("firstName").ascending()
//				  .and(Sort.by("lastName").descending());
//
//		handleList("findAll(Sort sort)", employeeService.findAll(sort));
//
//		Pageable pageable = 
//				PageRequest.of(0, 3, Sort.by("lastName").descending().and(Sort.by("firstName")));
//		
//		handleList("findByLastName(\"Bauer\", pageable)", employeeService.findByLastName("Bauer", pageable));
//
//		Pageable pageableAll = 
//				PageRequest.of(0, 3, Sort.by("lastName").descending().and(Sort.by("firstName")));
//		Page<Employee> pageResult = employeeService.findAll(pageableAll);
//
//		handleList("findAll(pageableAll)", pageResult.getContent());
//		
//		
//		Employee emp = employeeService.findOneByEmail("kkaok@a.a");
//		System.out.println(emp.toString());
//
//		log.info("");
	}

	//@Test
	void __test() throws JsonProcessingException {
	    
	    List<Dept> depts = new ArrayList<>();
	    depts.add(new Dept("dev", "개발팀"));
	    depts.add(new Dept("hr", "인사팀"));
	    depts.add(new Dept("sales", "영업팀"));
	    
	    deptService.saveAll(depts);
	    
	    List<Employee> employees = new ArrayList<>();
//	    employees.add(new Employee("dev", "Jack", "Bauer", "a1@a.a"));
//	    employees.add(new Employee("dev","Chloe", "O'Brian", "a2@a.a"));
//	    employees.add(new Employee("hr","Kim", "Bauer", "a3@a.a"));
//	    employees.add(new Employee("sales","David", "Palmer", "a4@a.a"));
//	    employees.add(new Employee("sales","Michelle", "Dessler", "kkaok@a.a"));
	    employeeService.saveAll(employees);
	    
	    handleList("findAll", employeeService.findAll());
	    
	    // fetch an individual customer by ID
	    log.info("한건 테스트 시작 ");
	    Employee employee = employeeService.findById(4L);
	    log.info("--------------------------------");
	    log.info("Employee found with findById(4L):");
	    log.info("--------------------------------");
	    log.info(employee.toString());
	    log.info("한건 테스트 종료 ");
	    
	    
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
