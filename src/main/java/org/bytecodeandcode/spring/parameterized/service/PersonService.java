package org.bytecodeandcode.spring.parameterized.service;

import org.bytecodeandcode.spring.batch.persistence.dao.PersonRepository;
import org.bytecodeandcode.spring.batch.persistence.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	public Person savePerson(Person person) {
		return repository.saveAndFlush(person);
	}
	
	public Person findPersonById(long id) {
		return repository.findOne(id);
	}
	
	public void deletePersonById(long id) {
		repository.delete(id);
	}

	public Long countPersons() {
		return repository.count();
	}
}
