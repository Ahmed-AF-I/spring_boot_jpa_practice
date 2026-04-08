package com.workshop.bouali.dto.employeedto;

import com.workshop.bouali.models.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {}

    /**
     * Converts an Employee entity to an EmployeeResponseDTO.
     *
     * @param employee the Employee entity to convert
     * @return EmployeeResponseDTO containing only the public fields
     */
    public static EmployeeResponseDTO toResponceDTO(Employee employee){
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
