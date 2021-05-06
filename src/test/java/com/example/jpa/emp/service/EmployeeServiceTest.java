package com.example.jpa.emp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.emp.dto.EmpAPI;
import com.example.jpa.emp.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest 
@ComponentScan("com.example.jpa")  // 스캔 범위 지정
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceTest {

	private EmployeeService employeeService;

    private ModelMapper modelMapper;

    public EmployeeServiceTest(@Autowired EmployeeService employeeService, @Autowired ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    @BeforeEach //Junit4의 @Before
    public void setup() {
        log.info("before start ######################################");
        Dept devDept = new Dept("dev", "개발팀");
        Employee emp1 = new Employee("Jack", "son", "a1@a.a");
        devDept.addEmployee(emp1); 
        employee1 = employeeService.save(emp1);

        Employee emp2 = new Employee("lee", "hi", "a2@a.a");
        devDept.addEmployee(emp2); 
        employee2 = employeeService.save(emp2);
        
        Dept devHr = new Dept("hr", "인사팀");
        Employee emp3 = new Employee("kkaok", "good", "kkaok@a.a");
        devHr.addEmployee(emp3); 
        employee3 = employeeService.save(emp3);
    }
    
    @Test
	void findById(){
	    Employee employee = employeeService.findById(employee1.getId());
        assertThat(employee.getEmail()).isSameAs(employee1.getEmail());
	}

    @Test
    void findOneByEmail(){
        Employee employee = employeeService.findOneByEmail(employee3.getEmail());
        assertThat(employee.getEmail()).isSameAs(employee3.getEmail());
    }
        
    @Test
    void findByLastName(){
        List<Employee> employees = employeeService.findByLastName(employee3.getLastName());
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(0)
            .contains(employee3)
            .doesNotContain(employee2);
    }

    @Test
    void findByFirstName() {
        List<Employee> employees = employeeService.findByFirstName(employee3.getFirstName());
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(0)
            .contains(employee3)
            .doesNotContain(employee2);
    }
    
    @Test
    void findAll() {
        List<Employee> employees = employeeService.findAll();
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(2);
    }
    
    @Test
    void findByFirstNameContaining() {
        List<Employee> employees = employeeService.findByFirstNameContaining("a");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(2)
            .contains(employee1)
            .contains(employee3)
            .doesNotContain(employee2);
    }

    @Test
    void findByFirstNameContainingIgnoreCase() {
        List<Employee> employees = employeeService.findByFirstNameContainingIgnoreCase("ja");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }

    @Test
    void findByFirstNameNotContaining() {
        List<Employee> employees = employeeService.findByFirstNameNotContaining("Ja");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .contains(employee3)
            .doesNotContain(employee1);
    }

    @Test
    void findByFirstNameLike() {
        List<Employee> employees = employeeService.findByFirstNameLike("%ac%");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }

    @Test
    void searchByFirstNameLike() {
        List<Employee> employees = employeeService.searchByFirstNameLike("%ac%");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }

    @Test
    void findByFirstNameNotLike() {
        List<Employee> employees = employeeService.findByFirstNameNotLike("%ac%");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .contains(employee3)
            .doesNotContain(employee1);
    }

    @Test
    void findByFirstNameStartsWith() {
        List<Employee> employees = employeeService.findByFirstNameStartsWith("kk");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee3)
            .doesNotContain(employee1)
            .doesNotContain(employee2);
    }
    
    @Test
    void findByFirstNameEndsWith() {
        List<Employee> employees = employeeService.findByFirstNameEndsWith("ee");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .doesNotContain(employee1)
            .doesNotContain(employee3);
    }

    @Test
    void searchByFirstNameStartsWith() {
        List<Employee> employees = employeeService.searchByFirstNameStartsWith("kk");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee3)
            .doesNotContain(employee1)
            .doesNotContain(employee2);
    }
    
    @Test
    void searchByFirstNameEndsWith() {
        List<Employee> employees = employeeService.searchByFirstNameEndsWith("ee");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .doesNotContain(employee1)
            .doesNotContain(employee3);
    }

    @Test
    void findByFirstNameAndLastName() {
        List<Employee> employees = employeeService.findByFirstNameAndLastName("Jack", "son");
        assertThat(employees)
            .isNotEmpty()
            .hasSize(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }
    
    @Test
    void findByFirstNameOrLastName() {
        List<Employee> employees = employeeService.findByFirstNameOrLastName("Jack", "good");
        assertThat(employees)
            .isNotEmpty()
            .hasSize(2)
            .contains(employee1)
            .contains(employee3)
            .doesNotContain(employee2);
        
    }

    @Test
    void sort() {
        Sort sort = Sort.by("firstName").ascending().and(Sort.by("lastName").descending());
        List<Employee> employees = employeeService.findAll(sort);
        assertThat(employees)
            .isNotEmpty()
            .hasSize(3);
//        employees.stream().forEach(System.out::println);
        assertThat(employees.get(0)).isEqualTo(employee1);
        assertThat(employees.get(1)).isEqualTo(employee3);
        assertThat(employees.get(2)).isEqualTo(employee2);
    }
    
    @Test
    void pageable() {
        int pageSize = 2;
        int currentPage = 0;
        Sort sort = Sort.by("firstName").ascending().and(Sort.by("lastName").descending());
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        Page<Employee> pageEmployees = employeeService.findAll(pageable);
        
        assertThat(pageEmployees).isNotEmpty();

        List<Employee> employees = pageEmployees.getContent();
        
        assertThat(employees)
            .isNotEmpty()
            .hasSize(2);
        assertThat(employees.get(0)).isEqualTo(employee1);
        assertThat(employees.get(1)).isEqualTo(employee3);
    }

    @Test
    void modelMapperTest() throws JsonProcessingException {
        EmpAPI api = modelMapper.map(employee1, EmpAPI.class);
        assertThat(api.getEmail()).isEqualTo(employee1.getEmail());
	}

}
