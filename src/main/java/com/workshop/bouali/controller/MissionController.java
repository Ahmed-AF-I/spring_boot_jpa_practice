package com.workshop.bouali.controller;

import com.workshop.bouali.dto.mission.MissionRequestDTO;
import com.workshop.bouali.dto.mission.MissionResponseDTO;
import com.workshop.bouali.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;


    @PostMapping
    public ResponseEntity<MissionResponseDTO> create(
            @RequestBody MissionRequestDTO request
    ){
        return new ResponseEntity<>(missionService.createMission(request), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MissionResponseDTO> updateMission(
            @PathVariable Long id,
            @RequestBody MissionRequestDTO request
    ){
        return ResponseEntity.ok(missionService.updateMission(id, request));
    }

    @GetMapping
    public ResponseEntity<List<MissionResponseDTO>> findAll() {
        return ResponseEntity.ok(missionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<MissionResponseDTO>> findMissionByEmployee(
            @PathVariable Integer employeeId
    ) {
        return ResponseEntity.ok(missionService.findMissionsByEmployee(employeeId));
    }
}
