package com.udemy.udemy.controller;

import com.udemy.udemy.model.Person;
import com.udemy.udemy.repository.FileStorageRepository;
import com.udemy.udemy.repository.PersonRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequestMapping("/people") // define the base path for all the sub routes
@Controller // tell the Spring that this call is used or act as router
public class PeopleController {

    private PersonRepository personRepository;
    private FileStorageRepository fileStorageRepository;

    @Autowired
    public PeopleController(PersonRepository personRepository, FileStorageRepository fileStorageRepository) {
        this.personRepository = personRepository;
        this.fileStorageRepository = fileStorageRepository;
    }


    @ModelAttribute("people")
    public Page<Person> findAll(@PageableDefault(size = 2) Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @GetMapping // define the get request path
    public String ShowPeoplePage(){
        return  "people"; // call the template from resources/templates/people.html
    }

    @GetMapping("/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource) {
        String dispo = """
                  attachment; filename="%s"
                """;
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, format(dispo,resource))
                .body (fileStorageRepository.findByName(resource)) ;
    }

    @ModelAttribute
    public Person personFormData(){ // model for incoming data from form
        Person object =  new Person(); // instance of the person call
        return object;
    }

    @SneakyThrows
    @PostMapping
    public String  SavePerson(@Valid Person person , Errors errors,@RequestParam("photo") MultipartFile picture){
        System.out.print(picture.getOriginalFilename());
        if(!errors.hasErrors()){
            fileStorageRepository.save(picture.getOriginalFilename(),picture.getInputStream());
            personRepository.save(person); // save the data into databasez
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
