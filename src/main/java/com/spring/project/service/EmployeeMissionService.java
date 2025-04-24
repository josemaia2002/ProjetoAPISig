package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.model.Employee;
import com.spring.project.model.Mission;

@Service
public class EmployeeMissionService {
    private final EmployeeService employeeService;
    private final MissionService missionService;

    @Autowired
    public EmployeeMissionService(EmployeeService employeeService, MissionService missionService) {
        this.employeeService = employeeService;
        this.missionService = missionService;
    }

    public List<Mission> findEmployeeMissions(Long employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);

        return employee.getMissions();
    }

    public void assignMissionToEmployee(Long employeeId, Long missionId) {
        Employee employee = employeeService.findEmployeeById(employeeId);

        Mission mission = missionService.findMissionById(missionId);

        // mission.get().getEmployees().contains(employee)
        if(!employee.getMissions().contains(mission)) {
            employee.getMissions().add(mission);
            employeeService.updateEmployee(employee, employeeId);
        }
    }

    public void removeEmployeeFromMission(Long employeeId, Long missionId) {
        Employee employee = employeeService.findEmployeeById(employeeId);
        Mission mission = missionService.findMissionById(missionId);

        employee.getMissions().remove(mission);
        
        employeeService.updateEmployee(employee, employeeId);
    }
}