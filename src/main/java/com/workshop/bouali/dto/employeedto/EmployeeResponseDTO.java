package com.workshop.bouali.dto.employeedto;

import lombok.Builder;

@Builder
public record EmployeeResponseDTO(
        Integer id,
        String firstName,
        String lastName,
        String email
) {}
