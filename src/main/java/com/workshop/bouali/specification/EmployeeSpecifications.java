package com.workshop.bouali.specification;

import com.workshop.bouali.models.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecifications {

    public static Specification<Employee> hasFirstName(String firstName){
        return (root, criteriaQuery, criteriaBuilder) ->
                (firstName == null || firstName.isBlank() ? null :
                        criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
    }

    public static Specification<Employee> hasLastName(String lastName){
        return (root, criteriaQuery, criteriaBuilder) ->
                (lastName == null || lastName.isBlank() ? null :
                        criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
    }

    public static Specification<Employee> hasEmail(String email) {
        return (root, query, cb) ->
                (email == null || email.isBlank()) ? null : cb.equal(root.get("email"), email);
    }
}
