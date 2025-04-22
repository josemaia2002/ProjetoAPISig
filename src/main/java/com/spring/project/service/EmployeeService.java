package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }

    public void createEmployee(Employee employee) {
        // Department employeeDepartment = employee.getDepartment();

        if(employee.getDepartment() != null) {
            Long departmentId = employee.getDepartment().getId();

            if(departmentId == null) {
                throw new IllegalArgumentException("Employee must be assigned to a valid department");
            }

            Department department = departmentService.findDepartmentById(departmentId);

            employee.setDepartment(department);
            department.getEmployees().add(employee);
        }

        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee, Long id) {
        Employee updatedEmployee = findEmployeeById(id);

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setBirthDate(employee.getBirthDate());

        employeeRepository.save(updatedEmployee);
    }

    public void updateEmployeeDepartment(Long employeeId, Long departmentId) {
        Employee updatedEmployee = findEmployeeById(employeeId);

        Department department = departmentService.findDepartmentById(departmentId);

        updatedEmployee.setDepartment(department);

        employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        
        employeeRepository.delete(employee);
    }
}