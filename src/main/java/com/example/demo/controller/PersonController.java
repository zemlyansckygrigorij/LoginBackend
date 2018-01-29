package com.example.demo.controller;

import java.security.Principal;
import java.util.Collection;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/people")
public class PersonController {

  @Autowired
  private PersonRepository personRepo;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<Person>> getPeople() {
    return new ResponseEntity<>(personRepo.findAll(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Person> getPerson(@PathVariable long id) {
    Person person = personRepo.findOne(id);

    if (person != null) {
      return new ResponseEntity<>(personRepo.findOne(id), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(/*null,*/ HttpStatus.NOT_FOUND);

    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> addPerson(@RequestBody Person person) {
    return new ResponseEntity<>(personRepo.save(person), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deletePerson(@PathVariable long id, Principal principal) {
    Person currentPerson = personRepo.findOne(id);

    if (currentPerson.getId() == id) {
      personRepo.delete(id);
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }
  }






}
