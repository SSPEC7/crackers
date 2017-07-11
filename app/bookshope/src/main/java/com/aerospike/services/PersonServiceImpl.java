package com.aerospike.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aerospike.client.query.IndexType;
import com.aerospike.models.Person;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void save(Person person) {
	
		personRepository.createIndex(Person.class, "Person_firstName_index", "firstname", IndexType.STRING);
		personRepository.createIndex(Person.class, "Person_age_index", "age", IndexType.NUMERIC);
		personRepository.save(person);
	}

	@Override
	public List<Person> findByAgeBetween(int from, int to) {
		
		personRepository.createIndex(Person.class, "Person_lastName_index", "lastName", IndexType.STRING);
		return personRepository.findByAgeBetween(from, to);
	}
	
	@Override
	public Person findById(String id){
		
		return personRepository.findOne(id);
	}
	
	@Override
	public List<Person> findByFirstName(String firstName){
		
		return personRepository.findByFirstname(firstName);
	}
	
	@Override
	public List<Person> findByLastName(String lastName){
		
		return personRepository.findByLastName(lastName);
	}
}
