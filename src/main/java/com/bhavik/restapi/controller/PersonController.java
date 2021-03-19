package com.bhavik.restapi.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bhavik.restapi.model.Person;

@RestController
public class PersonController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/jtGetAllPerson")
	public List<Person> getAllPerson() {

		return null;
	}

	@GetMapping("/jtGetOnePerson/{id}")
	public Person getOnePerson(@PathVariable int id) {

		Person p = jdbcTemplate.query("select * from person1 where id = ?", new ResultSetExtractor<Person>() {

			@Override
			public Person extractData(ResultSet rs) throws SQLException, DataAccessException {

				Person person1 = new Person();
				rs.next();

				person1.setId(rs.getInt("id"));
				person1.setName(rs.getString("name"));
				person1.setAge(rs.getInt("age"));
				person1.setSalary(rs.getInt("salary"));

				return person1;
			}

		}, id);
		return p;
	}

}
