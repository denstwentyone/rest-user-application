package com.example.restuserapplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restuserapplication.entity.PersonDTO;
import com.example.restuserapplication.service.PersonService;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    @GetMapping
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PersonDTO getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }
}
