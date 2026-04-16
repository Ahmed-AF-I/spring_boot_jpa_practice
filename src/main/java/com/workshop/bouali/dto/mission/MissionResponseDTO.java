package com.workshop.bouali.dto.mission;

import lombok.Builder;

@Builder
public record MissionResponseDTO(
   Long id,
   String name,
   Integer duration
) {}
