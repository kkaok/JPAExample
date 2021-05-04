package com.example.jpa.emp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.dept.service.DeptService;
import com.example.jpa.emp.dto.EmpAPI;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EmpAPIControllerTest2 {

    private final EmployeeService employeeService = Mockito.mock(EmployeeService.class);

    private final DeptService deptService = Mockito.mock(DeptService.class);
    
    EmpAPIController controller = new EmpAPIController(employeeService, deptService, modelMapper());
    
    private ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
          .setFieldMatchingEnabled(true)
          .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

    @Test
    public void getEmployee() throws Exception {

        // given
        Dept devDept = new Dept("dev", "개발팀");
        Employee emp = new Employee("Jack", "Bauer", "a1@a.a");
        devDept.addEmployee(emp); 
        given(employeeService.findById(anyLong())).willReturn(emp);

        // when
        EmpAPI result = controller.getEmp(anyLong());

        // then
        assertThat(result.getEmail()).isEqualTo(emp.getEmail());
//        assertThat(obj1).isSameAs(obj2);
//        assertThat(obj1).isNotNull();
//        assertThat(true).isTrue();

    }
}