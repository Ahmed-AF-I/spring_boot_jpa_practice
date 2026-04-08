package com.workshop.bouali.repositories.employeerepo;

import com.workshop.bouali.models.Employee;

import java.util.List;

/**
 *@Deprecated replaced with EmployeeSpecifications
 */

@Deprecated
public interface EmployeeRepositoryCustom {
    List<Employee> findBySimpleQuery(String firstName, String lastName, String email);
}
