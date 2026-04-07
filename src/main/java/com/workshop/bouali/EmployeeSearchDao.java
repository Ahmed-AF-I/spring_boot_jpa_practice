package com.workshop.bouali;

import com.workshop.bouali.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Deprecated
 */
@Deprecated
@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {

    private final EntityManager em;

    public List<Employee> findBySimpleQuery(
            String firstName,
            String lastName,
            String email
    ) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        /**
         * Root represents the root entity in the query
         * Specifies that the query starts from the Employee entity,
         * through which we can access all the columns and build the conditions.
         * allowing access to its attributes and building conditions.
         * Equivalent to: FROM employee e in SQL and JPQL.
         */
        Root<Employee> root = criteriaQuery.from(Employee.class);

        Predicate firstNamePredicate = criteriaBuilder
                .like(root.get("firstName"), "%" + firstName + "%");
        Predicate lastNamePredicate = criteriaBuilder
                .like(root.get("lastName"), "%" + lastName + "%");
        Predicate emailPredicate = criteriaBuilder
                .like(root.get("email"), "%" + email + "%");

        Predicate firstNameOrLastNamePredicate = criteriaBuilder
                .or(firstNamePredicate, lastNamePredicate);

        var andEmailPredicate = criteriaBuilder
                .and(firstNameOrLastNamePredicate, emailPredicate);
        criteriaQuery.where(andEmailPredicate);

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
