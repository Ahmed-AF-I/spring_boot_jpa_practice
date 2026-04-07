package com.workshop.bouali.controller;

import com.workshop.bouali.dto.EmployeeResponseDTO;
import com.workshop.bouali.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

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
    public ResponseEntity<List<EmployeeResponseDTO>> find(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email
    ){
        return ResponseEntity.ok(
                employeeService.searchEmployeesSpecification(firstName, lastName, email)
        );
    }
}
