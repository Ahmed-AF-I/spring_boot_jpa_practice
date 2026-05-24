package com.workshop.bouali.repositories.employeerepo;

import com.workshop.bouali.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>,
        JpaSpecificationExecutor<Employee>,
        EmployeeRepositoryCustom{
    Optional<Employee> findByEmail(String email);
}
