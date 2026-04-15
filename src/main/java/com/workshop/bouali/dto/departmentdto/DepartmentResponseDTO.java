package com.workshop.bouali.dto.departmentdto;

import lombok.Builder;

@Builder
public record DepartmentResponseDTO (
        Integer id,
        String name
){}
