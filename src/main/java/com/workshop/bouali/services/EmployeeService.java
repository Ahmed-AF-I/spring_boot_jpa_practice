package com.workshop.bouali.services;

import com.workshop.bouali.dto.EmployeeMapper;
import com.workshop.bouali.dto.EmployeeResponseDTO;
import com.workshop.bouali.models.Employee;
import com.workshop.bouali.repositories.employeerepo.EmployeeRepository;
import com.workshop.bouali.specification.EmployeeSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

    public List<EmployeeResponseDTO> searchEmployeesSpecification(
            String firstName,
            String lastName,
            String email
    ){
        Specification<Employee> spec = Specification
                .where(EmployeeSpecifications.hasFirstName(firstName))
                .and(EmployeeSpecifications.hasLastName(lastName))
                .and(EmployeeSpecifications.hasEmail(email));

        // get the date Compatible with Specification defined above
        List<Employee> employees = employeeRepository.findAll(spec);
        return employees.stream()
                .map(EmployeeMapper::toResponceDTO)
                .toList();
    }
}
