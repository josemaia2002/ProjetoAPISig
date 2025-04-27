package com.spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.dto.AddressResponseDTO;
import com.spring.project.dto.EmployeeRequestDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Department;
import com.spring.project.model.Employee;
import com.spring.project.model.EmployeeNotFoundException;
import com.spring.project.repository.EmployeeRepository;
import com.spring.project.util.mapper.EmployeeMapper;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
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

    public EmployeeResponseDTO findEmployeeById(Long employeeId) {
        Employee employee = findEmployeeEntityById(employeeId);

        EmployeeResponseDTO employeeResponseDTO = EmployeeMapper.INSTANCE.employeeToResponseDTO(employee);

        return employeeResponseDTO;
    }

    protected Employee findEmployeeEntityById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }

    public AddressResponseDTO findEmployeeAddress(Long employeeId) {
        EmployeeResponseDTO employeeResponseDTO = findEmployeeById(employeeId);

        return employeeResponseDTO.address();
    }


    public void createEmployee(EmployeeRequestDTO employeeRequestDTO) {
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

    public void updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long employeeId) {
        Employee employee = EmployeeMapper.INSTANCE.requestDTOToEmployee(employeeRequestDTO);
        
        updateEmployeeEntity(employee, employeeId);
    }

    protected void updateEmployeeEntity(Employee employee, Long employeeId) {
        Employee updatedEmployee = findEmployeeEntityById(employeeId);
        
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setBirthDate(employee.getBirthDate());

        
        employeeRepository.save(updatedEmployee);
    }

    public void updateEmployeeDepartment(Long employeeId, Long departmentId) {
        Employee updatedEmployee = findEmployeeEntityById(employeeId);

        Department department = departmentService.findDepartmentEntityById(departmentId);

        updatedEmployee.setDepartment(department);

        employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = findEmployeeEntityById(employeeId);

        employeeRepository.delete(employee);
    }
}