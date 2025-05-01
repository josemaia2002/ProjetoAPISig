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

import com.spring.project.dto.DepartmentRequestDTO;
import com.spring.project.dto.DepartmentResponseDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.service.DepartmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/departments")
@Validated
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<DepartmentResponseDTO> findAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{departmentId}")
    public DepartmentResponseDTO findDepartmentById(@PathVariable @Positive Long departmentId) {
        return departmentService.findDepartmentById(departmentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{departmentId}/employees")
    public List<EmployeeResponseDTO> findDepartmentEmployees(@PathVariable @Positive Long departmentId) {
        return departmentService.findDepartmentEmployees(departmentId);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createDepartment(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) {
        departmentService.createDepartment(departmentRequestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{departmentId}")
    public void updateDepartment(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO, @PathVariable @Positive Long departmentId) {
        departmentService.updateDepartment(departmentRequestDTO, departmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{departmentId}")
    public void deleteDepartment(@PathVariable @Positive Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}