package com.workshop.bouali.controller;

import com.workshop.bouali.dto.employeedto.EmployeeRequestDTO;
import com.workshop.bouali.dto.employeedto.EmployeeResponseDTO;
import com.workshop.bouali.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> create(@RequestBody EmployeeRequestDTO request) {
        return new ResponseEntity<>(employeeService.createEmployee(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody EmployeeRequestDTO request
    ) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> findAll() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{employeeId}/missions/{missionId}")
    public ResponseEntity<Void> addMission(
            @PathVariable Integer employeeId,
            @PathVariable Long missionId
    ) {
        employeeService.addMissionToEmployee(employeeId, missionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{employeeId}/missions/{missionId}")
    public ResponseEntity<Void> removeMission(
            @PathVariable Integer employeeId,
            @PathVariable Long missionId
    ) {
        employeeService.removeMissionFromEmployee(employeeId, missionId);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @Deprecate replaced with find
     */
    @Deprecated
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponseDTO>> search(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email
    ) {
        return ResponseEntity.ok(
                employeeService.searchEmployees(firstName, lastName, email)
        );
    }

    @GetMapping("/find")
    public ResponseEntity<?> find(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email
    ){

        if ((firstName == null || firstName.isBlank()) &&
            (lastName == null || lastName.isBlank()) &&
            (email == null || email.isBlank())){

            return ResponseEntity
                    .badRequest()
                    .body("you need to specify at least one parameter. ");
        }

        return ResponseEntity.ok(
                employeeService.searchEmployeesSpecification(firstName, lastName, email)
        );
    }
}
