package com.workshop.bouali.services;

import com.workshop.bouali.dto.EmployeeMapper;
import com.workshop.bouali.dto.EmployeeResponseDTO;
import com.workshop.bouali.repositories.employeerepo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponseDTO> searchEmployees(
            String firstName,
            String lastName,
            String email
    ){
        if(email != null && !email.isBlank() && !email.contains("@")){
            throw new IllegalArgumentException("Invalid email address");
        }
        return employeeRepository
                .findBySimpleQuery(firstName, lastName, email)
                .stream()
                .map(EmployeeMapper::toResponceDTO)
                .toList();
    }
}
