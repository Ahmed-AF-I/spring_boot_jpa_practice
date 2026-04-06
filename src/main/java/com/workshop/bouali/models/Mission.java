package com.workshop.bouali.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Mission {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer duration;

    @ManyToMany(mappedBy = "missions")
    private List<Employee> employees;
}
