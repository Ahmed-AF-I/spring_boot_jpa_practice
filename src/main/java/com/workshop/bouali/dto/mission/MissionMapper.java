package com.workshop.bouali.dto.mission;

import com.workshop.bouali.models.Mission;

public class MissionMapper {

    private MissionMapper() {}

    public static MissionResponseDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.builder()
                .id(mission.getId())
                .name(mission.getName())
                .duration(mission.getDuration())
                .build();
    }

    public static Mission toEntity(MissionRequestDTO request) {
        if (request == null) return null;

        Mission mission = new Mission();
        mission.setName(request.name());
        mission.setDuration(request.duration());
        return mission;
    }
}
