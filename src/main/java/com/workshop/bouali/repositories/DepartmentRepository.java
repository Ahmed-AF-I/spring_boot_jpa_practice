package com.workshop.bouali.repositories;

import com.workshop.bouali.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>,
        JpaSpecificationExecutor<Department> {}
