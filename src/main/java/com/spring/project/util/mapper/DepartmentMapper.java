package com.spring.project.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.spring.project.dto.DepartmentRequestDTO;
import com.spring.project.dto.DepartmentResponseDTO;
import com.spring.project.model.Department;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentResponseDTO departmentToResponseDTO(Department department);
    
    Department requestDTOToDepartment (DepartmentRequestDTO departmentRequestDTO);
}