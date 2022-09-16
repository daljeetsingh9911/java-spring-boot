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
                new Person(null,"a@gmailcom","singh1","daljeet", LocalDate.of(1991,10,02),new BigDecimal("10000"),null),
                new Person(null,"b@gmailcom","singh2","daljeet", LocalDate.of(1992,10,03),new BigDecimal("20000"),null),
                new Person(null,"c@gmailcom","singh3","daljeet", LocalDate.of(1993,12,04),new BigDecimal("50000"),null),
                new Person(null,"d@gmailcom","singh4","daljeet", LocalDate.of(1994,10,02),new BigDecimal("350000"),null),
                new Person(null,"e@gmailcom","singh5","daljeet", LocalDate.of(1995,10,03),new BigDecimal("50000"),null),
                new Person(null,"f@gmailcom","singh6","daljeet", LocalDate.of(1996,12,04),new BigDecimal("505000"),null),
                new Person(null,"g@gmailcom","singh7","daljeet", LocalDate.of(1997,10,02),new BigDecimal("503000"),null),
                new Person(null,"h@gmailcom","singh8","daljeet", LocalDate.of(1998,10,03),new BigDecimal("200100"),null),
                new Person(null,"i@gmailcom","singh9","daljeet", LocalDate.of(1999,12,04),new BigDecimal("540000"),null)
        ); // create a dummy object with person model structure
        personRepository.saveAll(personList); // save the value into inmemory database
    }
}
