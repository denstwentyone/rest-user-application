package com.example.restuserapplication.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.restuserapplication.entity.Person;
import com.example.restuserapplication.entity.PersonDTO;
import com.example.restuserapplication.repository.PersonRepository;

@Component
public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    public List<PersonDTO> getAllPersons() {
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> result = new ArrayList<>();
        for (Person person : personList) {
            result.add(PersonToDTO(person));
        }
        return result;
    }

    public PersonDTO getPersonById(Long id) {
        if (personRepository.findById(id).isPresent()) {
            Person person = personRepository.findById(id).get();
            return PersonToDTO(person);
        }
        return null;
    }

    private static Integer gatAge(Date date) {
        LocalDate localDate = LocalDate.parse(date.toString());
        return Period.between(localDate, LocalDate.now()).getYears();
    }

    public static PersonDTO PersonToDTO(Person person) {
        return new PersonDTO(person.getName(), person.getSurname(), gatAge(person.getBirth()));
    }
}
