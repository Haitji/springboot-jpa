package com.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;


import com.springboot.jpa.springbootjpa.model.Person;
import com.springboot.jpa.springbootjpa.model.PersonDTO;
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
		//create();
		// delete();
		//list();
		//findOneById();
		//personalizedQuery2();
		personalizedQueryDistinct();
		personalizedQueryDistinctCount();
	}

	public void list(){
		List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.buscarPorProgrammingLanguages("Java");
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguagesAndName("Java","Andres");
		//List<Object[]> personsValues = repository.obtenerNombrePorLenguaje("Java");
		persons.stream().forEach(person -> {
			System.out.println(person);
		});

		// personsValues.stream().forEach(person -> {
		// 	System.out.println(person[0]+" es experto en " + person[1]);
		// });
	}

	@Transactional(readOnly = true)
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
		repository.findOneLikeName("se").ifPresent(System.out::println);
		repository.findByNameContaining("se").ifPresent(System.out::println);// es igual que el like pero este viene por defecto del jpa repository
	}

	@Transactional(readOnly = true)
	public void findOneById(){
        Object[] reg = (Object[]) repository.buscarPersonaPorid(1L);
		System.out.println("Id: "+reg[0]+" Nombre: "+reg[1]+" Apellido: "+reg[2]+" Lenguaje programacion: "+reg[3]);
	}

	@Transactional
	public void create(){
		Person person = new Person(null, "Haitian", "Ji", "Springboot");
		//if(repository.findByNameAndLastname(person.getName(),person.getLastname()).isEmpty()){
			repository.save(person);
		//}
		
		List<Person> per = (List<Person>) repository.findAll();
		per.stream().forEach(p -> {
			System.out.println(p);
		});
	}

	@Transactional
	public void uptade(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Id: ");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresent(p -> {
			System.out.println(p);
			System.out.println("Ingrese el nuevo lenguaje de programacion: ");
			String lenguaje = scanner.next();//usamos next porque nextLine no espera y se ejecuta directamente
			p.setProgrammingLanguages(lenguaje);
			Person per = repository.save(p);
			System.out.println(per);
		});
		scanner.close();
	}

	@Transactional
	public void delete(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Id: ");
		Long id = scanner.nextLong();
		scanner.close();
		Optional<Person> optionalPerson = repository.findById(id);
		// if(optionalPerson.isPresent()){
		// 	repository.delete(optionalPerson.get());//Podemos eliminar por Person o por id que seria deleteById
		// }else{
		// 	System.out.println("El id introducido no existe");
		// }
		optionalPerson.ifPresentOrElse(/*p -> repository.delete(p)*/repository::delete, () -> System.out.println("El id introducido no existe"));
	}

	@Transactional(readOnly = true)
	public void personalizedQuery(){
		System.out.println("=================================Consulta personalizada===================================");
		List<Object[]> personReg = repository.findAllMixedPerson();

		personReg.forEach(reg->{
			System.out.println("Programming language -- "+reg[1]+ " \nPerson --"+reg[0]);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQuery2(){
		System.out.println("=================================Consulta personalizada===================================");
		List<PersonDTO> personReg = repository.findAllPerzonalizedPersonDTO();

		personReg.forEach(reg->{
			System.out.println(reg);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueryDistinct(){
		System.out.println("=================================Consulta personalizada name ditinct===================================");
		List<String> personReg = repository.allNameDistinct();

		personReg.forEach(reg->{
			System.out.println(reg);
		});

		System.out.println("=================================Consulta personalizada languages ditinct===================================");
		List<String> LanguagesReg = repository.allProgrammingLanguagesDistinct();

		LanguagesReg.forEach(reg->{
			System.out.println(reg);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueryDistinctCount(){
		System.out.println("=================================Consulta personalizada languages total ditinct===================================");
		Long LanguagesRegCount = repository.allProgrammingLanguagesDistinctCount();

		System.out.println("Total de lenguages de programaciobn: "+LanguagesRegCount+" ===================================");
	}

}
