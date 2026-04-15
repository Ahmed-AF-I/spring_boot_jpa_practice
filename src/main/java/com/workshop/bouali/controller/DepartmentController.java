package com.workshop.bouali.controller;

import com.workshop.bouali.dto.departmentdto.DepartmentRequestDTO;
import com.workshop.bouali.dto.departmentdto.DepartmentResponseDTO;
import com.workshop.bouali.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/search")
    public ResponseEntity<?> find(
            @RequestParam (required = false) Integer id,
            @RequestParam (required = false) String name
    ){
        if(id == null && StringUtils.hasText(name)) {
            return ResponseEntity.badRequest()
                    .body("you need to specify at least one parameter");
        }

        return ResponseEntity.ok(
                departmentService.searchDepartments(id, name)
        );
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @Valid @RequestBody DepartmentRequestDTO requestDTO
            ){
        DepartmentResponseDTO created = departmentService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable Integer id,
            @Valid @RequestBody DepartmentRequestDTO request
    ) {
        DepartmentResponseDTO updated = departmentService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Integer id) {
        DepartmentResponseDTO department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }
}
