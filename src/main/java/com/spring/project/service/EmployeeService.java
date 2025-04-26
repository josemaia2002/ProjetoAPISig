package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Address;
import com.spring.project.model.Department;
import com.spring.project.model.Employee;
import com.spring.project.model.EmployeeNotFoundException;
import com.spring.project.repository.EmployeeRepository;

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
        // TODO convertToDTO
        return employeeRepository.findAll();
    }

    public EmployeeResponseDTO findEmployeeById(Long employeeId) {
        // TODO convertToDTO

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }

    public Address findEmployeeAddress(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);

         // TODO convertToDTO
        
        return employee.getAddress();
    }


    public void createEmployee(Employee employee) {
        if(employee.getDepartment() != null) {
            Long departmentId = employee.getDepartment().getId();

            if(departmentId == null) {
                throw new IllegalArgumentException("Employee must be assigned to a valid department");
            }

            Department department = departmentService.findDepartmentById(departmentId);

            employee.setDepartment(department);
            department.getEmployees().add(employee);
        }

        // convertToEntity
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee, Long employeeId) {
        Employee updatedEmployee = findEmployeeById(employeeId);

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setBirthDate(employee.getBirthDate());

        // convertToEntity
        employeeRepository.save(updatedEmployee);
    }

    public void updateEmployeeDepartment(Long employeeId, Long departmentId) {
        Employee updatedEmployee = findEmployeeById(employeeId);

        Department department = departmentService.findDepartmentById(departmentId);

        updatedEmployee.setDepartment(department);

        // convertToEntity
        employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);

        // convertToEntity
        
        employeeRepository.delete(employee);
    }
}