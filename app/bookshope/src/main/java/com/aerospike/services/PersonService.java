package com.aerospike.services;

import java.util.List;

import com.aerospike.models.Person;

public interface PersonService {

	void save(Person person);
	
	List<Person> findByAgeBetween(int from,int to);

	Person findById(String id);

	List<Person> findByFirstName(String firstName);

	List<Person> findByLastName(String lastName);
}
