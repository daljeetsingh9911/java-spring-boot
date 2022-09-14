package com.udemy.udemy.repository;

import com.udemy.udemy.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class PersonDataloader implements ApplicationRunner {

    private  PersonRepository personRepository;

    public PersonDataloader(PersonRepository PersonRepository) {
        this.personRepository = PersonRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception { // this function when our application get started
        List<Person> personList = List.of(
                new Person(null,"d@gmailcom","singh1","daljeet", LocalDate.of(1991,10,02),new BigDecimal("50000")),
                new Person(null,"a@gmailcom","singh2","daljeet", LocalDate.of(1992,10,03),new BigDecimal("50000")),
                new Person(null,"b@gmailcom","singh3","daljeet", LocalDate.of(1993,12,04),new BigDecimal("50000"))
        ); // create a dummy object with person model structure
        personRepository.saveAll(personList); // save the value into inmemory database
    }
}
