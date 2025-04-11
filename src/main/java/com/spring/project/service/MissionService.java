package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.model.Mission;
import com.spring.project.model.MissionNotFoundException;
import com.spring.project.repository.MissionRepository;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;

    public List<Mission> findAllMissions() {
        return missionRepository.findAll();
    }

    public Mission findMissionById(Long id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if(mission.isEmpty()) {
            throw new MissionNotFoundException();
        }

        return mission.get();
    }

    public void createMission(Mission mission) {
        missionRepository.save(mission);
    }

    public void updateMission(Mission mission, Long id) {
        Mission updatedMission = findMissionById(id);

        updatedMission.setName(mission.getName());
        updatedMission.setDuration(mission.getDuration());

        missionRepository.save(updatedMission);
    }

    public void deleteMission(Long id) {
        Mission mission = findMissionById(id);

        missionRepository.delete(mission);
    }
}