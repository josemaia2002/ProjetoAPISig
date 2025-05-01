package com.spring.project.service;

import java.util.List;

import com.spring.project.dto.DepartmentRequestDTO;
import com.spring.project.dto.DepartmentResponseDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Department;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public interface DepartmentService {
    List<DepartmentResponseDTO> findAllDepartments();

    DepartmentResponseDTO findDepartmentById(@Positive Long departmentId);

    Department findDepartmentEntityById(@Positive Long departmentId);

    List<EmployeeResponseDTO> findDepartmentEmployees(@Positive  Long departmentId);

    void createDepartment(@Valid DepartmentRequestDTO departmentRequestDTO);

    void updateDepartment(@Valid DepartmentRequestDTO departmentRequestDTO, @Positive Long departmentId);

    void deleteDepartment(@Positive Long departmentId);
}