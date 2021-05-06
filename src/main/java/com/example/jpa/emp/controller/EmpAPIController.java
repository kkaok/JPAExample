package com.example.jpa.emp.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.emp.dto.EmpAPI;
import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;

//@Slf4j
@RestController
public class EmpAPIController {

    private final EmployeeService employeeService;

    private final ModelMapper modelMapper;

    public EmpAPIController(@Autowired EmployeeService employeeService, @Autowired ModelMapper modelMapper) {
        this.employeeService = employeeService;
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

}
