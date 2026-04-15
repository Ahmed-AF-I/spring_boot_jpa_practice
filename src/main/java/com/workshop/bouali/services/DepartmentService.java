package com.workshop.bouali.services;

import com.workshop.bouali.dto.departmentdto.DepartmentMapper;
import com.workshop.bouali.dto.departmentdto.DepartmentRequestDTO;
import com.workshop.bouali.dto.departmentdto.DepartmentResponseDTO;
import com.workshop.bouali.models.Department;
import com.workshop.bouali.repositories.DepartmentRepository;
import com.workshop.bouali.specification.DepartmentSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponseDTO> searchDepartments(
            Integer id,
            String name
    ) {
        Specification<Department> spec = Specification
                .where(DepartmentSpecification.byId(id))
                .and(DepartmentSpecification.byName(name));

        List<Department> departments = departmentRepository.findAll(spec);

        return departments.stream()
                .map(DepartmentMapper::toDTO)
                .toList();
    }

    @Transactional
    public DepartmentResponseDTO create(DepartmentRequestDTO request) {
        Department department = DepartmentMapper.toEntity(request);
        Department saved = departmentRepository.save(department);
        return DepartmentMapper.toDTO(saved);
    }

    @Transactional
    public DepartmentResponseDTO update(Integer id, DepartmentRequestDTO request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Department with id " + id + " not found"
                ));

        department.setName(request.name());
        return DepartmentMapper.toDTO(department);
    }

    @Transactional
    public void delete(Integer id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Department with id " + id + " not found"
            );
        }
        departmentRepository.deleteById(id);
    }

    public List<DepartmentResponseDTO> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentMapper::toDTO)
                .toList();
    }

    public DepartmentResponseDTO findById(Integer id){
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department with id " + id + " not found"));
        return DepartmentMapper.toDTO(department);
    }

}
