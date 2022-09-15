package com.udemy.udemy.controller;

import com.udemy.udemy.model.Person;
import com.udemy.udemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/people") // define the base path for all the sub routes
@Controller // tell the Spring that this call is used or act as router
public class PeopleController {

    private PersonRepository personRepository;

    @Autowired
    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ModelAttribute("people")
    public  Iterable<Person> getPeople(){
        return  personRepository.findAll();
    }

    @GetMapping // define the get request path
    public String ShowPeoplePage(){
        return  "people"; // call the template from resources/templates/people.html
    }

    @ModelAttribute
    public Person personFormData(){ // model for incoming data from form
        Person object =  new Person(); // instance of the person call
        return object;
    }

    @PostMapping
    public String  SavePerson(@Valid Person person , Errors errors){
        if(!errors.hasErrors()){
            personRepository.save(person); // save the data into database
            return  "redirect:people"; //redirect the page into main method
        }
        return  "people";
    }

    @PostMapping(params = "delete=true")
    public String  DeletePersons(@RequestParam("checkBox[]") List<Long> DeletedIds){
        //@RequestParam("checkBox[]")  = name of input
        //List<Long>   = type cast into array of Long ids
        // DeletedIds any user defined variable

        if(!DeletedIds.isEmpty()){
            System.out.print(DeletedIds);
           personRepository.deleteAllById(DeletedIds);
        }
        return  "redirect:people";
    }

    @PostMapping(params = "edit=true")
    public String  EditPersons(@RequestParam("checkBox[]") List<Long> DeletedIds,Model model){
        //@RequestParam("checkBox[]")  = name of input
        //List<Long>   = type cast into array of Long ids
        // DeletedIds any user defined variable

        if(!DeletedIds.isEmpty()){
           Optional<Person> person = personRepository.findById(DeletedIds.get(0)); // fetch first record from the array table
            model.addAttribute("person",person);
        }
        return  "people";
    }

}
