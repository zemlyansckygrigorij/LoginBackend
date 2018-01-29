package com.example.demo.repository;

import java.util.Collection;

import com.example.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

  Collection<Person> findAll();

  Person findByFirstname(String name);

}
