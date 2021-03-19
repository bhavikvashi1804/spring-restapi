package com.bhavik.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhavik.restapi.model.Person;
import com.bhavik.restapi.service.PersonService;

@RestController
public class AppController {
	
	@Autowired
	private PersonService service;

	
	//get all the person
	@GetMapping("/persons")
	public List<Person> getAllPersons() {
		return service.gellAllPerson();
	}

	
	//get one person based on ID
	@GetMapping("/person/{id}")
	public Person getPerson(@PathVariable int id) throws Exception {
		return service.getPerson(id);
	}
	
	//get one person based on ID and age
	@GetMapping("/person/{id}/{age}")
	public Person getPerson1(@PathVariable int id,@PathVariable int age) throws Exception{
		return service.getPerson(id,age);
	}
	
	
	//get one person: better way: handle the exception
	@GetMapping("/getPerson/{id}")
	public ResponseEntity<Object> getOnePerson(@PathVariable int id){
		Person p = new Person();
		try {
			p = service.getPerson(id);
		}
		catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(p,HttpStatus.OK);
	}
	
	
	//Add a new Person
	 @PostMapping("/persons")
	 public Person addNewPerson(@RequestBody Person person) {
		service.addNewPerson(person);
	    return person;
	  }
}
