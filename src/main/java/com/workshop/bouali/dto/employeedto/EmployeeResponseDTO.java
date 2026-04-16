package com.workshop.bouali.dto.employeedto;

import com.workshop.bouali.dto.mission.MissionSummaryDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record EmployeeResponseDTO(
        Integer id,
        String firstName,
        String lastName,
        String fullName,
        String email,
        String identifier,
        String role,
        String departmentName,
        String fullAddress,
        List<MissionSummaryDTO> missions
) {}
