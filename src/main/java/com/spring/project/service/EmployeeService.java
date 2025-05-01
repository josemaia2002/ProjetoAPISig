package com.spring.project.service;

import java.util.List;

import com.spring.project.dto.AddressResponseDTO;
import com.spring.project.dto.EmployeeRequestDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public interface EmployeeService {
    List<EmployeeResponseDTO> findAllEmployees();

    EmployeeResponseDTO findEmployeeById(@Positive Long employeeId);

    Employee findEmployeeEntityById(@Positive Long employeeId);

    AddressResponseDTO findEmployeeAddress(@Positive Long employeeId);

    void createEmployee(@Valid EmployeeRequestDTO employeeRequestDTO);

    void updateEmployee(@Valid EmployeeRequestDTO employeeRequestDTO, @Positive Long employeeId);

    void updateEmployeeEntity(Employee employee, Long employeeId);

    void updateEmployeeDepartment(@Positive Long employeeId, @Positive Long departmentId);

    void deleteEmployee(@Positive Long employeeId);
}