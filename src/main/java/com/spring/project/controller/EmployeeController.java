package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.model.Employee;
import com.spring.project.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping(value = "/")
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping(value = "/{id}")
    public Employee findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }


    
}

