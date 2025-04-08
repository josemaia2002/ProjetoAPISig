package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
