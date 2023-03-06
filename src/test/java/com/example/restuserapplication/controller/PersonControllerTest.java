package com.example.restuserapplication.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.restuserapplication.entity.Person;
import com.example.restuserapplication.repository.PersonRepository;



@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    Person person1;
    Person person2;

    @Autowired
    private MockMvc mvc;

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
    public void getPersonByIdTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/person/" + person1.getId()))
                                            .andExpect(status().isOk())
                                            .andExpect(content()
                                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                            .andExpect(content().string("{\"name\":\"name1\",\"surname\":\"surname1\",\"age\":23}"));

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/person/3"))
                                            .andExpect(status().isOk())
                                            .andExpect(content().string(""))
                                            .andExpect(header().doesNotExist("content-type"));

    }

    @Test
    public void getAllPersons() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/person"))
                                            .andExpect(status().isOk())
                                            .andExpect(content()
                                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                            .andExpect(content().string("[{\"name\":\"name1\",\"surname\":\"surname1\",\"age\":23},{\"name\":\"name2\",\"surname\":\"surname2\",\"age\":23}]"));
    }
}
