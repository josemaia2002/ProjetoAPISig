package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.dto.MissionResponseDTO;
import com.spring.project.service.EmployeeMissionService;

@RestController
@RequestMapping("/api/employees/{employeeId}/missions")
public class EmployeeMissionController {
    @Autowired
    private EmployeeMissionService employeeMissionService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<MissionResponseDTO> findEmployeeMissions(@PathVariable Long employeeId) {
        return employeeMissionService.findEmployeeMissions(employeeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{missionId}")
    public void assignMissionToEmployee(@PathVariable Long employeeId, @PathVariable Long missionId) {
        employeeMissionService.assignMissionToEmployee(employeeId, missionId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{missionId}")
    public void removeEmployeeFromMission(@PathVariable Long employeeId, @PathVariable Long missionId) {
        employeeMissionService.removeEmployeeFromMission(employeeId, missionId);
    }
}