package com.example.jpa.emp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.jpa.addr.entity.Address;
import com.example.jpa.config.DataBaseConfiguration;
import com.example.jpa.config.MapperConfiguration;
import com.example.jpa.dept.entity.Dept;
import com.example.jpa.emp.dto.EmpAPI;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.repository.EmployeeRepositorySupport;
import com.fasterxml.jackson.core.JsonProcessingException;

//@Slf4j
//@RunWith(SpringRunner.class)
//@ComponentScan("com.example.jpa")  // 스캔 범위 지정
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest 
@Import({
    DataBaseConfiguration.class,
    MapperConfiguration.class,
    EmployeeService.class,
    EmployeeRepositorySupport.class
})
class EmployeeServiceTest2 {

	private static EmployeeService employeeService;

    private static ModelMapper modelMapper;

    private static Employee employee1;
    private static Employee employee2;
    private static Employee employee3;

    @BeforeAll //Junit4의 @Before
    static void setup(@Autowired EmployeeService employeeService, @Autowired ModelMapper modelMapper) {
        EmployeeServiceTest2.employeeService = employeeService;
        EmployeeServiceTest2.modelMapper = modelMapper;
        
        Dept devDept = Dept.builder().deptCd("dev").deptName("개발팀").build(); 
        Employee emp1 = Employee.builder().firstName("Jack").lastName("son").email("a1@a.a").build();
        devDept.addEmployee(emp1); 
        Address addr1 = Address.builder().address("광명시").build();
        emp1.setAddress(addr1);
        employee1 = EmployeeServiceTest2.employeeService.save(emp1);

        Employee emp2 = Employee.builder().firstName("lee").lastName("hi").email("a2@a.a").build();
        devDept.addEmployee(emp2); 
        Address addr2 = Address.builder().address("강남구").build();
        emp2.setAddress(addr2);
        employee2 = EmployeeServiceTest2.employeeService.save(emp2);
        
        Dept devHr = Dept.builder().deptCd("hr").deptName("인사팀").build();
        Employee emp3 = Employee.builder().firstName("kkaok").lastName("good").email("kkaok@a.a").build();
        devHr.addEmployee(emp3); 
        Address addr3 = Address.builder().address("논현동").build();
        emp3.setAddress(addr3);
        employee3 = EmployeeServiceTest2.employeeService.save(emp3);
    }
    
    @Test
    @DisplayName("findById() : 한건 조회 테스트")
	void testFindById(){
	    Employee employee = EmployeeServiceTest2.employeeService.findById(employee1.getId());
        assertThat(employee.getEmail()).isSameAs(employee1.getEmail());
	}

    @Test
    @DisplayName("findOneByEmail() : 이메일로 조회 테스트")
    void testFindOneByEmail(){
        Employee employee = EmployeeServiceTest2.employeeService.findOneByEmail(employee3.getEmail());
        assertThat(employee.getEmail()).isSameAs(employee3.getEmail());
    }
        
    @Test
    @DisplayName("findByLastName() : lastName으로 조회 테스트")
    void testFindByLastName(){
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByLastName(employee3.getLastName());
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(0)
            .contains(employee3)
            .doesNotContain(employee2);
    }

    @Test
    @DisplayName("findByFirstName() : firstName으로 조회 테스트")
    void testFindByFirstName() {
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstName(employee3.getFirstName());
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(0)
            .contains(employee3)
            .doesNotContain(employee2);
    }
    
    @Test
    @DisplayName("findAll() : 목록 조회 테스트")
    void testFindAll() {
        List<Employee> employees = EmployeeServiceTest2.employeeService.findAll();
        assertThat(employees)
            .isNotEmpty()
            .hasSizeGreaterThan(2);
    }
    
    @Test
    @DisplayName("findByFirstNameContaining() : containing 목록 조회 테스트")
    void testFindByFirstNameContaining() {
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameContaining("a");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameContainingIgnoreCase("ja");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameNotContaining("Ja");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameLike("%ac%");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.searchByFirstNameLike("%ac%");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameNotLike("%ac%");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameStartsWith("kk");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameEndsWith("ee");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.searchByFirstNameStartsWith("kk");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.searchByFirstNameEndsWith("ee");
        
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameAndLastName("Jack", "son");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findByFirstNameOrLastName("Jack", "good");
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
        List<Employee> employees = EmployeeServiceTest2.employeeService.findAll(sort);
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
        Page<Employee> pageEmployees = EmployeeServiceTest2.employeeService.findAll(pageable);
        
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
