package com.spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.spring.project.dto.DepartmentRequestDTO;
import com.spring.project.dto.DepartmentResponseDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Department;
import com.spring.project.model.DepartmentNotFoundException;
import com.spring.project.model.Employee;
import com.spring.project.repository.DepartmentRepository;
import com.spring.project.util.mapper.DepartmentMapper;
import com.spring.project.util.mapper.EmployeeMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponseDTO> findAllDepartments() {
        List<DepartmentResponseDTO> departments = new ArrayList<>();

        for(Department department: departmentRepository.findAll()) {
            DepartmentResponseDTO departmentResponseDTO = DepartmentMapper.INSTANCE.departmentToResponseDTO(department);
            departments.add(departmentResponseDTO);
        }

        return departments;
    }

    public DepartmentResponseDTO findDepartmentById(@Positive Long departmentId) {
        Department department = findDepartmentEntityById(departmentId);
        DepartmentResponseDTO departmentResponseDTO = DepartmentMapper.INSTANCE.departmentToResponseDTO(department);
        

        return departmentResponseDTO;
    }

    protected Department findDepartmentEntityById(@Positive Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(DepartmentNotFoundException::new);

        return department;
    }

    public List<EmployeeResponseDTO> findDepartmentEmployees(@Positive  Long departmentId) {
        Department department = findDepartmentEntityById(departmentId);

        List<EmployeeResponseDTO> employees = new ArrayList<>();

        for(Employee employee: department.getEmployees()) {
            EmployeeResponseDTO employeeResponseDTO = EmployeeMapper.INSTANCE.employeeToResponseDTO(employee);
            employees.add(employeeResponseDTO);
        }

        return employees;
    }

    public void createDepartment(@Valid DepartmentRequestDTO departmentRequestDTO) {
        Department department = DepartmentMapper.INSTANCE.requestDTOToDepartment(departmentRequestDTO);

        departmentRepository.save(department);
    }

    public void updateDepartment(@Valid DepartmentRequestDTO departmentRequestDTO, @Positive Long departmentId) {
        Department updatedDepartment = findDepartmentEntityById(departmentId);
        
        Department department = DepartmentMapper.INSTANCE.requestDTOToDepartment(departmentRequestDTO);

        updatedDepartment.setName(department.getName());

        departmentRepository.save(updatedDepartment);
    }

    public void deleteDepartment(@Positive Long departmentId) {
        Department department = findDepartmentEntityById(departmentId);

        departmentRepository.delete(department);
    }
}