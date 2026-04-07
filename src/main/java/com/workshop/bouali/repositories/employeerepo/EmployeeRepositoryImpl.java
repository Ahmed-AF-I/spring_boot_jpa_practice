package com.workshop.bouali.repositories.employeerepo;

import com.workshop.bouali.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Employee> findBySimpleQuery(
            String firstName,
            String lastName,
            String email
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        /**
         * Root entity of the query.
         * Equivalent to: FROM employee e in SQL.
         * Used to access entity fields and build predicates.
         */
        Root<Employee> root = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null && !firstName.isBlank()) {
            predicates.add(cb.like(root.get("firstName"), "%" + firstName + "%"));
        }

        if (lastName != null && !lastName.isBlank()) {
            predicates.add(cb.like(root.get("lastName"), "%" + lastName + "%"));
        }

        if (email != null && !email.isBlank()) {
            predicates.add(cb.equal(root.get("email"), email));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Employee> query = em.createQuery(cq);
        return query.getResultList();
    }
}