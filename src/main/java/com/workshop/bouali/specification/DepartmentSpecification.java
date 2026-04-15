package com.workshop.bouali.specification;

import com.workshop.bouali.models.Department;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentSpecification {
    public static Specification<Department> byName(String name) {
        return (root, _, cb) ->
                (name == null || name.isBlank()) ? null :
                        cb.equal(root.get("name"), name);
    }

    public static Specification<Department> byId(Integer id) {
        return (root, _, cb) ->
                (id == null) ? null : cb.equal(root.get("id"), id);
    }
}
