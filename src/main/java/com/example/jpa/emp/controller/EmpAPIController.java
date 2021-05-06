package com.example.jpa.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.dept.service.DeptService;
import com.example.jpa.emp.dto.EmpAPI;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmpAPIController {

    private final EmployeeService employeeService;

    private final DeptService deptService;

    private final ModelMapper modelMapper;

    public EmpAPIController(@Autowired EmployeeService employeeService, @Autowired DeptService deptService, @Autowired ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.deptService = deptService;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping("/emp-all")
    public List<Employee> getEmpAll() throws JsonProcessingException {
        return employeeService.findAll();
    }

    @GetMapping("/emps/{id}")
    public EmpAPI getEmp(@PathVariable("id") Long id) throws JsonProcessingException {
        Employee employee = employeeService.findById(id);
        return  modelMapper.map(employee, EmpAPI.class);
    }

    @GetMapping("/create")
    public String create() throws JsonProcessingException {
        List<Dept> depts = new ArrayList<>();
        depts.add(new Dept("dev", "개발팀"));
        depts.add(new Dept("hr", "인사팀"));
        depts.add(new Dept("sales", "영업팀"));

        deptService.saveAll(depts);

        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(1L, "Jack", "Bauer", "a1@a.a"));
//        employees.add(new Employee(1L,"Chloe", "O'Brian", "a2@a.a"));
//        employees.add(new Employee(2L,"Kim", "Bauer", "a3@a.a"));
//        employees.add(new Employee(3L,"David", "Palmer", "a4@a.a"));
//        employees.add(new Employee(3L,"Michelle", "Dessler", "kkaok@a.a"));
        employeeService.saveAll(employees);
        return "ok";
    }
}
