package com.springboot.jpa.springbootjpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    @Column(name = "programming_language")
    private String programmingLanguages;

    
    public Person() {//JPA usa este constructor vacio, para crear una instancia de la clase cuando recupera los datos
    }

    public Person(Long id, String name, String lastName, String programmingLanguages) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.programmingLanguages = programmingLanguages;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastName) {
        this.lastname = lastName;
    }
    public String getProgrammingLanguages() {
        return programmingLanguages;
    }
    public void setProgrammingLanguages(String programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    
}
