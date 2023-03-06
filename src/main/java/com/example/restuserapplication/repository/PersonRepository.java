package com.example.restuserapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restuserapplication.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    public Optional<Person> findById(Long id);

}
