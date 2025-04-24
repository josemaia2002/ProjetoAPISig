package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.model.Employee;
import com.spring.project.model.Mission;
import com.spring.project.service.MissionService;

@RestController
@RequestMapping("/api/missions")
public class MissionController {
    @Autowired
    private MissionService missionService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<Mission> findAllMissions() {
        return missionService.findAllMissions();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Mission findMissionById(@PathVariable Long id) {
        return missionService.findMissionById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/employees")
    public List<Employee> findMissionEmployees(@PathVariable Long id) {
        return missionService.findMissionEmployees(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createMission(@RequestBody Mission mission) {
        missionService.createMission(mission);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void updatedMission(@RequestBody Mission mission, @PathVariable Long id) {
        missionService.updateMission(mission, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
    }
}