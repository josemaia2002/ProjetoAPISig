package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
