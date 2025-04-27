package com.spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.dto.MissionRequestDTO;
import com.spring.project.dto.MissionResponseDTO;
import com.spring.project.model.Employee;
import com.spring.project.model.Mission;
import com.spring.project.model.MissionNotFoundException;
import com.spring.project.repository.MissionRepository;
import com.spring.project.util.mapper.EmployeeMapper;
import com.spring.project.util.mapper.MissionMapper;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;

    public List<MissionResponseDTO> findAllMissions() {
        List<MissionResponseDTO> missions = new ArrayList<>();

        for(Mission mission: missionRepository.findAll()) {
            MissionResponseDTO missionResponseDTO = MissionMapper.INSTANCE.missionToResponseDTO(mission);
            missions.add(missionResponseDTO);            
        }

        return missions;
    }

    public MissionResponseDTO findMissionById(Long missionId) {
        Mission mission = findMissionEntityById(missionId);

        MissionResponseDTO missionResponseDTO = MissionMapper.INSTANCE.missionToResponseDTO(mission);

        return missionResponseDTO;
    }

    protected Mission findMissionEntityById(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(MissionNotFoundException::new);
    }

    public List<EmployeeResponseDTO> findMissionEmployees(Long missionId) {
        List<EmployeeResponseDTO> employees = new ArrayList<>();
        
        Mission mission = findMissionEntityById(missionId);

        for(Employee employee: mission.getEmployees()) {
            EmployeeResponseDTO employeeResponseDTO = EmployeeMapper.INSTANCE.employeeToResponseDTO(employee);
            employees.add(employeeResponseDTO);      
        }

        return employees;
    }

    public void createMission(MissionRequestDTO missionRequestDTO) {
        Mission mission = MissionMapper.INSTANCE.requestDTOToMission(missionRequestDTO);

        missionRepository.save(mission);
    }

    public void updateMission(MissionRequestDTO missionRequestDTO, Long missionId) {
        Mission mission = MissionMapper.INSTANCE.requestDTOToMission(missionRequestDTO);
        Mission updatedMission = findMissionEntityById(missionId);

        updatedMission.setName(mission.getName());
        updatedMission.setDuration(mission.getDuration());

        missionRepository.save(updatedMission);
    }

    public void deleteMission(Long missionId) {
        Mission mission = findMissionEntityById(missionId);

        missionRepository.delete(mission);
    }
}