package com.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.jpa.springbootjpa.model.Person;
import com.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		findOne();
	}

	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.buscarPorProgrammingLanguages("Java");
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguagesAndName("Java","Andres");
		List<Object[]> personsValues = repository.obtenerNombrePorLenguaje("Java");
		// persons.stream().forEach(person -> {
		// 	System.out.println(person);
		// });

		personsValues.stream().forEach(person -> {
			System.out.println(person[0]+" es experto en " + person[1]);
		});
	}

	public void findOne(){
		//Person person = repository.findById(1L).orElseThrow();//tambien podemos usar el .get() en lugar del .orElseThrow()
        Person person = null;
		Optional<Person> optioanlPerson = repository.findById(1L);
		if(optioanlPerson.isPresent()){
			person = optioanlPerson.get();
		}
		System.out.println(person);


		//Esta es otra manera de obnetencion de un elemento
		repository.findById(1L).ifPresent(perso -> { System.out.println(perso);});
	}

}
