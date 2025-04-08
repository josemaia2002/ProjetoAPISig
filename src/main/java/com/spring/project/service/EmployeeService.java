package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.model.Employee;
import com.spring.project.model.EmployeeNotFoundException;
import com.spring.project.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    // Find all
    // Find by id
    // create
    // update
    // delete

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

    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        
        employeeRepository.delete(employee);
    }
}