package com.aerospike.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.aerospike.models.Person;

public interface PersonRepository extends AerospikeRepository<Person, String> {

	List<Person> findByLastName(String lastname);
    List<Person> findByLastNameStartsWith(String prefix);
    List<Person> findByLastNameEndsWith(String postfix);
    List<Person> findByLastNameOrderByFirstnameAsc(String lastname);
    List<Person> findByLastNameOrderByFirstnameDesc(String lastname);
    List<Person> findByFirstnameLike(String firstname);
    List<Person> findByFirstnameLikeOrderByLastNameAsc(String firstname, Sort sort);
    List<Person> findByAgeLessThan(int age, Sort sort);
    List<Person> findByFirstnameIn(String... firstnames);
    List<Person> findByFirstnameNotIn(Collection<String> firstnames);
    List<Person> findByFirstnameAndLastName(String firstname, String lastname);
    List<Person> findByAgeBetween(int from, int to);
    List<Person> findByAddressZipCode(String zipCode);
    List<Person> findByLastNameLikeAndAgeBetween(String lastname, int from, int to);
    List<Person> findByAgeOrLastNameLikeAndFirstnameLike(int age, String lastname, String firstname);
    List<Person> findByCreatedAtLessThan(Date date);
    List<Person> findByCreatedAtGreaterThan(Date date);
    List<Person> findByCreatedAtBefore(Date date);
    List<Person> findByCreatedAtAfter(Date date);
    List<Person> findByLastNameNot(String lastname);
    List<Person> findCustomerByAgeBetween(Integer from, Integer to);
    List<Person> findPersonByFirstname(String firstname);
    long countByLastName(String lastname);
    int countByFirstname(String firstname);
    long someCountQuery(String lastname);
    List<Person> findByFirstnameIgnoreCase(String firstName);
    List<Person> findByFirstnameNotIgnoreCase(String firstName);
    List<Person> findByFirstnameStartingWithIgnoreCase(String firstName);
    List<Person> findByFirstnameEndingWithIgnoreCase(String firstName);
    List<Person> findByFirstnameContainingIgnoreCase(String firstName);
    Slice<Person> findByAgeGreaterThan(int age, Pageable pageable);
    List<Person> deleteByLastname(String lastname);
    Long deletePersonByLastName(String lastname);
    List<Person> findTop3ByLastNameStartingWith(String lastname);

    Page<Person> findTop3ByLastNameStartingWith(String lastname, Pageable pageRequest);
    List<Person> findByFirstname(String string);
    List<Person> findByFirstnameAndAge(String string, int i);
    Iterable<Person> findByAgeBetweenAndLastName(int from, int to, String lastname);
    List<Person> findByFirstnameStartsWith(String string);
    Iterable<Person> findByAgeBetweenOrderByLastName(int i, int j);
}
