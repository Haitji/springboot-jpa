package com.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.springbootjpa.model.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{

    
}