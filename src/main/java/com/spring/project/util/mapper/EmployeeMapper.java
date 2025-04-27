package com.spring.project.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.spring.project.dto.EmployeeRequestDTO;
import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.model.Employee;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "address.street", source = "address.street")
    @Mapping(target = "address.houseNumber", source = "address.houseNumber")
    @Mapping(target = "address.zipCode", source = "address.zipCode")
    EmployeeResponseDTO employeeToResponseDTO(Employee employee);
    

    Employee RequestDTOToEmployee (EmployeeRequestDTO employeeRequestDTO);
}