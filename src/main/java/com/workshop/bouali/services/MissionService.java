package com.workshop.bouali.services;

import com.workshop.bouali.dto.mission.MissionMapper;
import com.workshop.bouali.dto.mission.MissionRequestDTO;
import com.workshop.bouali.dto.mission.MissionResponseDTO;
import com.workshop.bouali.models.Mission;
import com.workshop.bouali.repositories.MissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional
    public MissionResponseDTO createMission(
            MissionRequestDTO request
    ){
        Mission mission = MissionMapper.toEntity(request);
        return MissionMapper.toMissionDTO(missionRepository.save(mission));
    }

    @Transactional
    public MissionResponseDTO updateMission(Long id, MissionRequestDTO request) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        mission.setName(request.name());
        mission.setDuration(request.duration());

        return MissionMapper.toMissionDTO(missionRepository.save(mission));
    }

    public List<MissionResponseDTO> findAll() {
        return missionRepository.findAll().stream()
                .map(MissionMapper::toMissionDTO)
                .toList();
    }

    public MissionResponseDTO findById(Long id) {
        return missionRepository.findById(id)
                .map(MissionMapper::toMissionDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Mission not found with id: " + id));
    }

    @Transactional
    public void deleteMission(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Mission not found with id: " + id);
        }
        missionRepository.deleteById(id);
    }

    public List<MissionResponseDTO> findMissionsByEmployee(Integer employeeId) {
        return missionRepository.findByEmployees_Id(employeeId).stream()
                .map(MissionMapper::toMissionDTO)
                .toList();
    }
}
