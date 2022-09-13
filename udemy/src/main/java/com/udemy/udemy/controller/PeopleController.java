package com.udemy.udemy.controller;

import com.udemy.udemy.model.Person;
import com.udemy.udemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/people") // define the base path for all the sub routes
@Controller // tell the Spring that this call is used or act as router
public class PeopleController {

    private PersonRepository personRepository;

    @Autowired
    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //@ModelAttribute("people")
    public  Iterable<Person> getPeople(){
        return  personRepository.findAll();
    }

    @GetMapping // define the get request path
    public String ShowPeoplePage(Model model){
        model.addAttribute("people",getPeople());
        return  "people"; // call the template from resources/templates/people.html
    }
}
