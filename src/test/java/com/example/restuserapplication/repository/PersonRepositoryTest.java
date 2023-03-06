package com.example.restuserapplication.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.restuserapplication.entity.Person;

@SpringBootTest
public class PersonRepositoryTest {

    Person person1;
    Person person2;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        person1 = Person.builder()
                        .birth(Date.valueOf("2000-01-01"))
                        .name("name1")
                        .surname("surname1")
                        .build();

        person2 = Person.builder()
                        .birth(Date.valueOf("2000-01-01"))
                        .name("name2")
                        .surname("surname2")
                        .build();

        personRepository.save(person1);
        personRepository.save(person2);
    }

    @AfterEach
    public void teardown() {
        personRepository.deleteAll();
    }

    @Test
    public void findAllTest() {
        assertEquals(List.of(person1, person2), personRepository.findAll());
    }

    @Test
    public void findById() {
        assertEquals(Optional.of(person1), personRepository.findById(person1.getId()));
    }
    
}
