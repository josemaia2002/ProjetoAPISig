package com.spring.project.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.spring.project.dto.MissionRequestDTO;
import com.spring.project.dto.MissionResponseDTO;
import com.spring.project.model.Mission;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionMapper {
    MissionMapper INSTANCE = Mappers.getMapper(MissionMapper.class);

    MissionResponseDTO missionToResponseDTO(Mission mission);

    Mission requestDTOToMission(MissionRequestDTO missionRequestDTO);
}
