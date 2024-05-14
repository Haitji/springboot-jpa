package com.springboot.jpa.springbootjpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.springbootjpa.model.Person;
import java.util.List;


public interface PersonRepository extends CrudRepository<Person,Long>{

    //Las operaciones normales no hace falta declararlas porque se puede usar directamente

    List<Person> findByProgrammingLanguages(String programmingLanguages);
    List<Person> findByProgrammingLanguagesAndName(String programmingLanguages,String name);

    @Query("select p from Person p where p.programmingLanguages=?1")//Hcaemos consulta sobre la clase persona en lugar de la tabla
    List<Person> buscarPorProgrammingLanguages(String programmingLanguages);

    @Query("select p.name, p.programmingLanguages from Person p where p.programmingLanguages=?1")
    List<Object[]> obtenerNombrePorLenguaje(String programmingLanguages);

    @Query("select p.name, p.programmingLanguages from Person p where p.programmingLanguages=?1 and p.name = ?2")
    List<Object[]> obtenerNombrePorLenguaje(String programmingLanguages,String name);
}