package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public Mission findMissionById(Long id) {
        return missionService.findMissionById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createMission(Mission mission) {
        missionService.createMission(mission);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void updatedMission(Mission mission, Long id) {
        missionService.updateMission(mission, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteMission(Long id) {
        missionService.deleteMission(id);
    }
}