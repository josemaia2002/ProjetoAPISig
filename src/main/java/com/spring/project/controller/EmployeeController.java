package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.dto.AddressResponseDTO;
import com.spring.project.dto.EmployeeRequestDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<EmployeeResponseDTO> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public EmployeeResponseDTO findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/address")
    public AddressResponseDTO findEmployeeAddress(@PathVariable Long id) {
        return employeeService.findEmployeeAddress(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createEmployee(@RequestBody EmployeeRequestDTO employee) {
        employeeService.createEmployee(employee);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void updateEmployee(@RequestBody EmployeeRequestDTO employee, @PathVariable Long id) {
        employeeService.updateEmployee(employee, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{employeeId}/department/{departmentId}")
    public void updateEmployeeDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
        employeeService.updateEmployeeDepartment(employeeId, departmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}