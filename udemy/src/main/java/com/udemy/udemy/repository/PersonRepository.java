package com.udemy.udemy.repository;

import com.udemy.udemy.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person,Long > {

}
