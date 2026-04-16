package com.workshop.bouali.dto.mission;

import lombok.Builder;

@Builder
public record MissionSummaryDTO(
        Long id,
        String name,
        Integer duration
) {}
