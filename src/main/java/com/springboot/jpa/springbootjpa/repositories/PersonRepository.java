package com.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.springbootjpa.model.Person;
import com.springboot.jpa.springbootjpa.model.PersonDTO;


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

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String string);

    Optional<Person> findByNameAndLastname(String name, String lastname);

    @Query("select p.id,p.name, p.lastname, p.programmingLanguages from Person p where p.id = ?1")
    Object buscarPersonaPorid(Long id);

    @Query("select p, p.programmingLanguages from Person p")
    List<Object[]> findAllMixedPerson();

    @Query("select new Person(null,p.name,p.lastname,'') from Person p")
    List<Person> findAllPerzonalizedPerson();

    @Query("select new com.springboot.jpa.springbootjpa.model.PersonDTO(p.name,p.lastname) from Person p")//es necesario poner todo el package para que encuntre donde esta la clase, porque al no estar anotada con un @Entity no reconoce/encuentra esto
    List<PersonDTO> findAllPerzonalizedPersonDTO();

    @Query("select distinct(p.name) from Person p")
    List<String> allNameDistinct();

    @Query("select distinct(p.programmingLanguages) from Person p")
    List<String> allProgrammingLanguagesDistinct();

    @Query("select count(distinct(p.programmingLanguages)) from Person p")
    Long allProgrammingLanguagesDistinctCount();

    @Query("select concat(p.name, ' ',p.lastname) from Person p")
    List<String> findFullName();

    @Query("select p.name|| ' '||p.lastname from Person p")//esta es otra manera de concatenar
    List<String> findFullName2();

    @Query("select upper(p.name)|| ' '||lower(p.lastname) from Person p")
    List<String> findFullNameUpperLowerCase();

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc, p.id desc")//ejemplo de order by
    List<Person> findBetweenId(Long num1,Long num2);

    List<Person> findByIdBetweenOrderByIdDesc(Long n1,Long n2);//otra manera de hacer consulta

    @Query("select count(p) from Person p")
    Long totalPerson();

    @Query("select max(p.id) from Person p")
    Long maxId();

    @Query("select min(p.id) from Person p")
    Long minId();

    @Query("select p.name,LENGTH(p.name) from Person p")
    List<Object[]> getNameLenght();

    @Query("select p.name,LENGTH(p.name) from Person p where length(p.name) = (select min(length(p.name)) from Person p)")
    List<Object[]> getShorterNameLenght();

    @Query("select p from Person p where p.id in ?1")
    List<Person> getPersonByIds(List<Long> ids);
}