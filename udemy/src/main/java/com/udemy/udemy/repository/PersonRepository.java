package com.udemy.udemy.repository;

import com.udemy.udemy.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long > {
}
