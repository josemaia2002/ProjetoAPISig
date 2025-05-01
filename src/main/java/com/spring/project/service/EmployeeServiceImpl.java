package com.spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.spring.project.dto.AddressResponseDTO;
import com.spring.project.dto.EmployeeRequestDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Department;
import com.spring.project.model.Employee;
import com.spring.project.model.EmployeeNotFoundException;
import com.spring.project.repository.EmployeeRepository;
import com.spring.project.util.mapper.EmployeeMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public List<EmployeeResponseDTO> findAllEmployees() {
        List<EmployeeResponseDTO> employees = new ArrayList<>();

        for(Employee employee: employeeRepository.findAll()) {
            EmployeeResponseDTO employeeDTO = EmployeeMapper.INSTANCE.employeeToResponseDTO(employee);
            employees.add(employeeDTO);
        }

        return employees;
    }

    public EmployeeResponseDTO findEmployeeById(@Positive Long employeeId) {
        Employee employee = findEmployeeEntityById(employeeId);

        EmployeeResponseDTO employeeResponseDTO = EmployeeMapper.INSTANCE.employeeToResponseDTO(employee);

        return employeeResponseDTO;
    }

    public Employee findEmployeeEntityById(@Positive Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }

    public AddressResponseDTO findEmployeeAddress(@Positive Long employeeId) {
        EmployeeResponseDTO employeeResponseDTO = findEmployeeById(employeeId);

        return employeeResponseDTO.address();
    }


    public void createEmployee(@Valid EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = EmployeeMapper.INSTANCE.requestDTOToEmployee(employeeRequestDTO);

        if(employee.getDepartment() != null) {
            Long departmentId = employee.getDepartment().getId();

            if(departmentId == null) {
                throw new IllegalArgumentException("Employee must be assigned to a valid department");
            }

            Department department = departmentService.findDepartmentEntityById(departmentId);

            employee.setDepartment(department);
            department.getEmployees().add(employee);
        }

        employeeRepository.save(employee);
    }

    public void updateEmployee(@Valid EmployeeRequestDTO employeeRequestDTO, @Positive Long employeeId) {
        Employee employee = EmployeeMapper.INSTANCE.requestDTOToEmployee(employeeRequestDTO);
        
        updateEmployeeEntity(employee, employeeId);
    }

    public void updateEmployeeEntity(Employee employee, Long employeeId) {
        Employee updatedEmployee = findEmployeeEntityById(employeeId);
        
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setBirthDate(employee.getBirthDate());

        
        employeeRepository.save(updatedEmployee);
    }

    public void updateEmployeeDepartment(@Positive Long employeeId, @Positive Long departmentId) {
        Employee updatedEmployee = findEmployeeEntityById(employeeId);

        Department department = departmentService.findDepartmentEntityById(departmentId);

        updatedEmployee.setDepartment(department);

        employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(@Positive Long employeeId) {
        Employee employee = findEmployeeEntityById(employeeId);

        employeeRepository.delete(employee);
    }
}