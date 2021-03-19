package com.bhavik.restapi.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

		Person p = jdbcTemplate.query("select * from person1 where id = ?", new PersonExtract(), id);
		return p;
	}

	@PostMapping("/jtAddPerson")
	public ResponseEntity<Object> registerPerson(@RequestBody Person p) {

		int noOfRowsUpdated = 0;

		try {

			noOfRowsUpdated = jdbcTemplate.update("insert into person1 values (?,?,?,?)", p.getId(), p.getName(),
					p.getAge(), p.getSalary());
		} catch (Exception e) {
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (noOfRowsUpdated > 0) {
			return new ResponseEntity(p, HttpStatus.OK);
		} else {
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/*
		 * Method Get -> Post Body > Raw > JSON Select { "id":107, "name":"Atul",
		 * "age":54, "salary":35000 }
		 * 
		 */
	}

	@PutMapping("/jtPutPerson")
	public ResponseEntity<Object> updateOnePerson(Person p) {

		int noOfRowsUpdated = 0;

		try {

			noOfRowsUpdated = jdbcTemplate.update("update person ", p.getId(), p.getName(), p.getAge(), p.getSalary());
		} catch (Exception e) {
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (noOfRowsUpdated > 0) {
			return new ResponseEntity(p, HttpStatus.OK);
		} else {
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/jtDeletePerson/{id}")
	public ResponseEntity<Object> deleteOnePerson(@PathVariable int id){
		int noOfRowsUpdated = 0;

		try {

			noOfRowsUpdated = jdbcTemplate.update("delete from person1 where id = ? ",id);
		} catch (Exception e) {
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (noOfRowsUpdated > 0) {
			return new ResponseEntity("Delete done", HttpStatus.OK);
		} else {
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// url: http://localhost:8080/jtDeletePerson/107
	}
	

}

class PersonExtract implements ResultSetExtractor<Person> {

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

}
