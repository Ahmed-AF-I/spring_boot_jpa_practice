package com.workshop.bouali.dto.departmentdto;

import com.workshop.bouali.models.Department;

public class DepartmentMapper {

    private DepartmentMapper() {}

    public static DepartmentResponseDTO toDTO(Department department) {
        return DepartmentResponseDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public static Department toEntity(DepartmentRequestDTO departmentRequestDTO) {
        if(departmentRequestDTO == null) {
            return null;
        }

        return Department.create(departmentRequestDTO.name());
    }
}
