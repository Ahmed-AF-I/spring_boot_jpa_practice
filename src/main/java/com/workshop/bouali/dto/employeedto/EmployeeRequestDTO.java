package com.workshop.bouali.dto.employeedto;

import com.workshop.bouali.models.EmployeeRole;

import java.time.LocalDate;
import java.util.List;

public record EmployeeRequestDTO(
        String firstName,
        String lastName,
        String email,
        String identifier,
        LocalDate birthDate,
        EmployeeRole role,
        Integer addressId,
        Integer departmentId,
        List<Long> missionIds
) {
    public EmployeeRequestDTO {
        missionIds = (missionIds != null) ? missionIds : List.of();
    }
}
