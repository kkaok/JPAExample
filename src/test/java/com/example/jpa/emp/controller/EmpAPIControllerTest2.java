package com.example.jpa.emp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.emp.dto.EmpAPI;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.service.EmployeeService;

//@Slf4j
class EmpAPIControllerTest2 {

    private final EmployeeService employeeService = Mockito.mock(EmployeeService.class);

    EmpAPIController controller = new EmpAPIController(employeeService, modelMapper());
    
    private ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
          .setFieldMatchingEnabled(true)
          .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

    @Test
    @DisplayName("testFindEmployee() : 조회 테스트")
    void testFindEmployee() throws Exception {

        // given
        Dept devDept = new Dept("dev", "개발팀");
        Employee emp = new Employee("Jack", "Bauer", "a1@a.a");
        devDept.addEmployee(emp); 
        given(employeeService.findById(anyLong())).willReturn(emp);

        // when
        EmpAPI result = controller.getEmp(anyLong());

        // then
        assertThat(result.getEmail()).isNotNull().isEqualTo(emp.getEmail());
//        assertThat(obj1).isSameAs(obj2);
//        assertThat(obj1).isNotNull();
//        assertThat(true).isTrue();

    }
}