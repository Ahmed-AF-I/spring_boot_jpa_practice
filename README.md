Employee Management System

This project is a basic training application built to practice database design and entity relationships using JPA.

Overview

The system models a simple organization structure with the following entities:

Employee
Address
Department
Mission

The main goal of this project is to understand how to map relational database tables into Java entities and handle different types of relationships.

Relationships Covered

The project includes practice on:

One-to-One
Example: Employee ↔ Address
One-to-Many
Example: Department → Employees
Many-to-Many
Example: Employee ↔ Missions
Technologies Used
Java
Spring Boot
Spring Data JPA (Hibernate)
PostgreSQL (or any relational database)
What I Practiced
Converting database tables into JPA entities
Using annotations like:
@Entity
@OneToOne
@OneToMany
@ManyToMany
@JoinColumn
@JoinTable
Understanding ownership of relationships
Handling bidirectional vs unidirectional mapping
Project Structure
entity/ → contains all entities
repository/ → JPA repository interfaces
service/ → application logic (if used)
controller/ → REST APIs (if used)
Notes

This project is for learning purposes only and focuses on understanding relationships in JPA rather than building a full production system.