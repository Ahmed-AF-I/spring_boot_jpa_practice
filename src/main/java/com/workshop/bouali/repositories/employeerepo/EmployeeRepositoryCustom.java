package com.workshop.bouali.repositories.employeerepo;

import com.workshop.bouali.models.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findBySimpleQuery(String firstName, String lastName, String email);
}
