package com.workshop.bouali.dto.employeedto;

import com.workshop.bouali.models.Address;
import com.workshop.bouali.models.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {}

    /**
     * Converts an Employee entity to an EmployeeResponseDTO.
     *
     * @param employee the Employee entity to convert
     * @return EmployeeResponseDTO containing only the public fields
     */
    public static EmployeeResponseDTO toResponse(Employee employee){
        if(employee == null) return null;

        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullName(employee.getFirstName() + " " + employee.getLastName())
                .email(employee.getEmail())
                .identifier(employee.getIdentifier())
                .role(employee.getRole().name())
                .departmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .fullAddress(formatAddress(employee.getAddress()))
                .build();
    }

    public static Employee toEntity(EmployeeRequestDTO request) {
        if(request == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setIdentifier(request.identifier());
        employee.setBirthDate(request.birthDate());
        employee.setRole(request.role());

        return employee;
    }

    private static String formatAddress(Address address){
        if (address == null) return "No Address";
        return String.format("%s, %s, %s",
                address.getHouseNumber(),
                address.getStreetName(),
                address.getZipCode());
    }
}
