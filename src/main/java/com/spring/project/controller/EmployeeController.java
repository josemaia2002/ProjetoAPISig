package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<EmployeeResponseDTO> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{employeeId}")
    public EmployeeResponseDTO findEmployeeById(@PathVariable @Positive Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{employeeId}/address")
    public AddressResponseDTO findEmployeeAddress(@PathVariable @Positive Long employeeId) {
        return employeeService.findEmployeeAddress(employeeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createEmployee(@Valid @RequestBody EmployeeRequestDTO employee) {
        employeeService.createEmployee(employee);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{employeeId}")
    public void updateEmployee(@Valid @RequestBody EmployeeRequestDTO employee, @PathVariable @Positive Long employeeId) {
        employeeService.updateEmployee(employee, employeeId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{employeeId}/department/{departmentId}")
    public void updateEmployeeDepartment(@PathVariable @Positive Long employeeId, @PathVariable @Positive Long departmentId) {
        employeeService.updateEmployeeDepartment(employeeId, departmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{employeeId}")
    public void deleteEmployee(@PathVariable @Positive Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}