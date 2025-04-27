package com.spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.dto.DepartmentRequestDTO;
import com.spring.project.dto.DepartmentResponseDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Department;
import com.spring.project.model.DepartmentNotFoundException;
import com.spring.project.model.Employee;
import com.spring.project.repository.DepartmentRepository;
import com.spring.project.util.mapper.DepartmentMapper;
import com.spring.project.util.mapper.EmployeeMapper;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentResponseDTO> findAllDepartments() {
        List<DepartmentResponseDTO> departments = new ArrayList<>();

        for(Department department: departmentRepository.findAll()) {
            DepartmentResponseDTO departmentResponseDTO = DepartmentMapper.INSTANCE.departmentToResponseDTO(department);
            departments.add(departmentResponseDTO);
        }

        return departments;
    }

    public DepartmentResponseDTO findDepartmentById(Long departmentId) {
        Department department = findDepartmentEntityById(departmentId);
        DepartmentResponseDTO departmentResponseDTO = DepartmentMapper.INSTANCE.departmentToResponseDTO(department);
        

        return departmentResponseDTO;
    }

    protected Department findDepartmentEntityById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);

        return department;
    }

    public List<EmployeeResponseDTO> findDepartmentEmployees(Long id) {
        Department department = findDepartmentEntityById(id);

        List<EmployeeResponseDTO> employees = new ArrayList<>();

        for(Employee employee: department.getEmployees()) {
            EmployeeResponseDTO employeeResponseDTO = EmployeeMapper.INSTANCE.employeeToResponseDTO(employee);
            employees.add(employeeResponseDTO);
        }

        return employees;
    }

    public void createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department = DepartmentMapper.INSTANCE.RequestDTOToDepartment(departmentRequestDTO);

        departmentRepository.save(department);
    }

    public void updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long departmentId) {
        Department updatedDepartment = findDepartmentEntityById(departmentId);
        
        Department department = DepartmentMapper.INSTANCE.RequestDTOToDepartment(departmentRequestDTO);

        updatedDepartment.setName(department.getName());

        departmentRepository.save(updatedDepartment);
    }

    public void deleteDepartment(Long departmentId) {
        Department department = findDepartmentEntityById(departmentId);

        departmentRepository.delete(department);
    }
}