package com.workshop.bouali.services;

import com.workshop.bouali.dto.employeedto.EmployeeMapper;
import com.workshop.bouali.dto.employeedto.EmployeeRequestDTO;
import com.workshop.bouali.dto.employeedto.EmployeeResponseDTO;
import com.workshop.bouali.models.Employee;
import com.workshop.bouali.models.Mission;
import com.workshop.bouali.repositories.DepartmentRepository;
import com.workshop.bouali.repositories.MissionRepository;
import com.workshop.bouali.repositories.addressrepo.AddressRepository;
import com.workshop.bouali.repositories.employeerepo.EmployeeRepository;
import com.workshop.bouali.specification.EmployeeSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;
    private final MissionRepository missionRepository;

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
                .map(EmployeeMapper::toResponse)
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

        // get the data Compatible with Specification defined above
        List<Employee> employees = employeeRepository.findAll(spec);
        return employees.stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }


    @Transactional
    public EmployeeResponseDTO createEmployee(
            EmployeeRequestDTO request
    ){
        Employee employee = EmployeeMapper.toEntity(request);
        assignedRelations(employee, request);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toResponse(savedEmployee);
    }

    @Transactional
    public EmployeeResponseDTO updateEmployee(
            Integer id,
            EmployeeRequestDTO request
    ){
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not founded with id: " + id));

        existingEmployee.setFirstName(request.firstName());
        existingEmployee.setLastName(request.lastName());
        existingEmployee.setEmail(request.email());
        existingEmployee.setRole(request.role());
        existingEmployee.setBirthDate(request.birthDate());

        assignedRelations(existingEmployee, request);

        Employee updated = employeeRepository.save(existingEmployee);
        return EmployeeMapper.toResponse(updated);
    }

    public List<EmployeeResponseDTO> findAllEmployees(){
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    public EmployeeResponseDTO findById(Integer id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    private void assignedRelations(Employee employee, EmployeeRequestDTO request){
        if(request.departmentId() != null) {
            var department = departmentRepository
                    .findById(request.departmentId())
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Department not found with id: " + request.departmentId()));
            employee.setDepartment(department);
        }

        if(request.addressId() != null) {
            var address = addressRepository
                    .findById(request.addressId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Address not found with id: " + request.addressId()));
            employee.setAddress(address);
        }

        if(request.missionIds() != null && !request.missionIds().isEmpty()) {
            List<Mission> missions = missionRepository
                    .findAllById(request.missionIds());

            if (missions.size() != request.missionIds().size()) {
                throw new EntityNotFoundException(
                        "Some missions not found in database");
            }
            employee.setMissions(missions);

        } else {
            if (employee.getMissions() == null) {
                employee.setMissions(List.of());
            }
        }
    }

    @Transactional
    public void addMissionToEmployee(Integer employeeId, Long missionId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found"));

        if (employee.getMissions() == null) {
            employee.setMissions(new ArrayList<>());
        }

        if (!employee.getMissions().contains(mission)) {
            employee.getMissions().add(mission);
        }
    }

    @Transactional
    public void removeMissionFromEmployee(Integer employeeId, Long missionId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        if (employee.getMissions() != null) {
            employee.getMissions().removeIf(m -> m.getId().equals(missionId));
        }
    }
}
