package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.dto.MissionResponseDTO;
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

    public List<MissionResponseDTO> findEmployeeMissions(Long employeeId) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.findEmployeeById(employeeId);

        return employeeResponseDTO.missions();
    }

    public void assignMissionToEmployee(Long employeeId, Long missionId) {
        Employee employee = employeeService.findEmployeeEntityById(employeeId);

        Mission mission = missionService.findMissionEntityById(missionId);

        // mission.get().getEmployees().contains(employee)
        if(!employee.getMissions().contains(mission)) {
            employee.getMissions().add(mission);
            employeeService.updateEmployeeEntity(employee, employeeId);
        }
    }

    public void removeEmployeeFromMission(Long employeeId, Long missionId) {
        Employee employee = employeeService.findEmployeeEntityById(employeeId);
        Mission mission = missionService.findMissionEntityById(missionId);

        employee.getMissions().remove(mission);
        
        employeeService.updateEmployeeEntity(employee, employeeId);
    }
}