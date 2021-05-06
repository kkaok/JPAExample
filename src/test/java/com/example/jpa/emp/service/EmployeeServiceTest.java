package com.example.jpa.emp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class EmployeeServiceTest {

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
    void setup() {
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
    @DisplayName("findById() : 한건 조회 테스트")
    void testFindById(){
        Employee employee = employeeService.findById(employee1.getId());
        assertThat(employee.getEmail()).isSameAs(employee1.getEmail());
    }

    @Test
    @DisplayName("findOneByEmail() : 이메일로 조회 테스트")
    void testFindOneByEmail(){
        Employee employee = employeeService.findOneByEmail(employee3.getEmail());
        assertThat(employee.getEmail()).isSameAs(employee3.getEmail());
    }
        
    @Test
    @DisplayName("findByLastName() : lastName으로 조회 테스트")
    void testFindByLastName(){
        List<Employee> employees = employeeService.findByLastName(employee3.getLastName());
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(0)
            .contains(employee3)
            .doesNotContain(employee2);
    }

    @Test
    @DisplayName("findByFirstName() : firstName으로 조회 테스트")
    void testFindByFirstName() {
        List<Employee> employees = employeeService.findByFirstName(employee3.getFirstName());
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(0)
            .contains(employee3)
            .doesNotContain(employee2);
    }
    
    @Test
    @DisplayName("findAll() : 목록 조회 테스트")
    void testFindAll() {
        List<Employee> employees = employeeService.findAll();
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(2);
    }
    
    @Test
    @DisplayName("findByFirstNameContaining() : containing 목록 조회 테스트")
    void testFindByFirstNameContaining() {
        List<Employee> employees = employeeService.findByFirstNameContaining("a");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(2)
            .contains(employee1)
            .contains(employee3)
            .doesNotContain(employee2);
    }

    @Test
    @DisplayName("findByFirstNameContainingIgnoreCase() : containing ignore case 목록 조회 테스트")
    void testFindByFirstNameContainingIgnoreCase() {
        List<Employee> employees = employeeService.findByFirstNameContainingIgnoreCase("ja");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }

    @Test
    @DisplayName("findByFirstNameNotContaining() : not containing 목록 조회 테스트")
    void testFindByFirstNameNotContaining() {
        List<Employee> employees = employeeService.findByFirstNameNotContaining("Ja");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .contains(employee3)
            .doesNotContain(employee1);
    }

    @Test
    @DisplayName("findByFirstNameLike() : firstName like 목록 조회 테스트")
    void testFindByFirstNameLike() {
        List<Employee> employees = employeeService.findByFirstNameLike("%ac%");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }

    @Test
    @DisplayName("searchByFirstNameLike() : firstName like 목록 search 테스트")
    void testSearchByFirstNameLike() {
        List<Employee> employees = employeeService.searchByFirstNameLike("%ac%");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }

    @Test
    @DisplayName("findByFirstNameNotLike() : firstName not like 목록 search 테스트")
    void testFindByFirstNameNotLike() {
        List<Employee> employees = employeeService.findByFirstNameNotLike("%ac%");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .contains(employee3)
            .doesNotContain(employee1);
    }

    @Test
    @DisplayName("findByFirstNameStartsWith() : Starts With 목록 조회 테스트")
    void testFindByFirstNameStartsWith() {
        List<Employee> employees = employeeService.findByFirstNameStartsWith("kk");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee3)
            .doesNotContain(employee1)
            .doesNotContain(employee2);
    }
    
    @Test
    @DisplayName("findByFirstNameEndsWith() : Ends With 목록 조회 테스트")
    void testFindByFirstNameEndsWith() {
        List<Employee> employees = employeeService.findByFirstNameEndsWith("ee");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .doesNotContain(employee1)
            .doesNotContain(employee3);
    }

    @Test
    @DisplayName("searchByFirstNameStartsWith() : Starts With 목록 search 테스트")
    void testSearchByFirstNameStartsWith() {
        List<Employee> employees = employeeService.searchByFirstNameStartsWith("kk");
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee3)
            .doesNotContain(employee1)
            .doesNotContain(employee2);
    }
    
    @Test
    @DisplayName("searchByFirstNameEndsWith() : Ends With 목록 search 테스트")
    void testSearchByFirstNameEndsWith() {
        List<Employee> employees = employeeService.searchByFirstNameEndsWith("ee");
        
        System.out.println("@@@@@@@@@");
        employees.stream().forEach(System.out::println);
        System.out.println("@@@@@@@@@");
        
        System.out.println("#########");
        System.out.println(employee2.toString());
        System.out.println("#########");
        
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1)
            .contains(employee2)
            .doesNotContain(employee1)
            .doesNotContain(employee3);
    }

    @Test
    @DisplayName("findByFirstNameAndLastName() : And 조건 목록 조회 테스트")
    void testFindByFirstNameAndLastName() {
        List<Employee> employees = employeeService.findByFirstNameAndLastName("Jack", "son");
        assertThat(employees)
            .isNotEmpty()
            .hasSize(1)
            .contains(employee1)
            .doesNotContain(employee2)
            .doesNotContain(employee3);
    }
    
    @Test
    @DisplayName("findByFirstNameOrLastName() : Or 조건 목록 조회 테스트")
    void testFindByFirstNameOrLastName() {
        List<Employee> employees = employeeService.findByFirstNameOrLastName("Jack", "good");
        assertThat(employees)
            .isNotEmpty()
            .hasSize(2)
            .contains(employee1)
            .contains(employee3)
            .doesNotContain(employee2);
        
    }

    @Test
    @DisplayName("sort() : 정렬 목록 조회 테스트")
    void testSort() {
        Sort sort = Sort.by("firstName").ascending().and(Sort.by("lastName").descending());
        List<Employee> employees = employeeService.findAll(sort);
        assertThat(employees)
            .isNotEmpty()
            .hasSize(3);
//        employees.stream().forEach(System.out::println);
        assertThat(employees.get(0)).isNotNull().isEqualTo(employee1);
        assertThat(employees.get(1)).isNotNull().isEqualTo(employee3);
        assertThat(employees.get(2)).isNotNull().isEqualTo(employee2);
    }
    
    @Test
    @DisplayName("pageable() : 페이징 목록 조회 테스트")
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
        assertThat(employees.get(0)).isNotNull().isEqualTo(employee1);
        assertThat(employees.get(1)).isNotNull().isEqualTo(employee3);
    }

    @Test
    @DisplayName("modelMapperTest() : model Mapper 테스트")
    void modelMapperTest() throws JsonProcessingException {
        EmpAPI api = modelMapper.map(employee1, EmpAPI.class);
        assertThat(api.getEmail()).isNotNull().isEqualTo(employee1.getEmail());
    }

}