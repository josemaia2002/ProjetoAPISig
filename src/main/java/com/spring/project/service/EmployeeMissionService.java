package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.model.Employee;
import com.spring.project.model.EmployeeNotFoundException;
import com.spring.project.model.MissionNotFoundException;
import com.spring.project.model.Mission;
import com.spring.project.repository.EmployeeRepository;
import com.spring.project.repository.MissionRepository;

@Service
public class EmployeeMissionService {
    private final EmployeeRepository employeeRepository;
    private final MissionRepository missionRepository;

    @Autowired
    public EmployeeMissionService(EmployeeRepository employeeRepository, MissionRepository missionRepository) {
        this.employeeRepository = employeeRepository;
        this.missionRepository = missionRepository;
    }

    public List<Mission> findEmployeeMissions(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get().getMissions();
    }

    // Use service classes
    public void assignMissionToEmployee(Long employeeId, Long missionId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        Optional<Mission> mission = missionRepository.findById(missionId);
        if(employee.isEmpty()) {
            throw new MissionNotFoundException();
        }

        // mission.get().getEmployees().contains(employee)
        if(!employee.get().getMissions().contains(mission.get())) {
            employee.get().getMissions().add(mission.get());
            employeeRepository.save(employee.get());
        }
    }
}