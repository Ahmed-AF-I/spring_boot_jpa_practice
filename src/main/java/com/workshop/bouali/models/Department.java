package com.workshop.bouali.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public static Department create(String name) {
        Department department = new Department();
        department.setName(name);
        return department;
    }

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
}
