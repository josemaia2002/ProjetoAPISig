package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.model.Department;
import com.spring.project.model.DepartmentNotFoundException;
import com.spring.project.repository.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()) {
            throw new DepartmentNotFoundException();
        }

        return department.get();
    }

    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void updateDepartment(Department department, Long id) {
        Department updateDepartment = findDepartmentById(id);

        updateDepartment.setName(department.getName());

        departmentRepository.save(updateDepartment);
    }

    public void deleteDepartment(Long id) {
        Department department = findDepartmentById(id);

        departmentRepository.delete(department);
    }
}