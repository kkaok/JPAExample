package com.example.jpa.emp.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
class EmpAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    @DisplayName("testFindEmployee() : 조회 테스트")
    void testFindEmployee() throws Exception {

        // given
        Dept devDept = new Dept("dev", "개발팀");
        Employee emp = new Employee("Jack", "Bauer", "a1@a.a");
        devDept.addEmployee(emp); 
        given(employeeService.findById(anyLong())).willReturn(emp);

        // when
        final ResultActions actions = mvc
                .perform(get("/emps/{id}", anyLong()).contentType(MediaType.APPLICATION_JSON)).andDo(print());
        // then
        actions.andExpect(status().isOk()).andExpect(jsonPath("deptCd").value(emp.getDept().getDeptCd()))
                .andExpect(jsonPath("firstName").value(emp.getFirstName()))
                .andExpect(jsonPath("email").value(emp.getEmail()));

    }
}