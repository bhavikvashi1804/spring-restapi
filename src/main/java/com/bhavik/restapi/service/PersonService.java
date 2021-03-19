package com.bhavik.restapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.bhavik.restapi.model.Person;

@Repository
public class PersonService {

	private static List<Person> personList;

	public PersonService() {
		personList = new ArrayList<>();

		personList.add(new Person(101, "Bhavik", 22, 20000));
		personList.add(new Person(102, "Raj", 23, 22000));
		personList.add(new Person(103, "Meet", 22, 20000));
		personList.add(new Person(104, "Yash", 23, 22000));
	}

	public List<Person> gellAllPerson() {
		return personList;
	}

	public Person getPerson(int id) throws Exception {
		Person p = new Person();
		
		try {
			p = personList.stream().filter(p1 -> p1.getId() == id).collect(Collectors.toList()).get(0);
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception();
		}
		return p;
	}

	public Person getPerson(int id, int age) {
		Person p = new Person();
		p = personList.stream().filter(p1 -> ((p1.getId() == id) && (p1.getAge() == age))).collect(Collectors.toList())
				.get(0);
		return p;
	}

	public void addNewPerson(Person p) {
		personList.add(p);
	}

}
