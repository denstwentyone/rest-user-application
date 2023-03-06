package com.example.restuserapplication.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    @Id
    @SequenceGenerator(
        name = "person_sequence",
        sequenceName="person_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "person_sequence"
    )
    @Column(
        nullable = false
    )
    private Long id;
    
    @Column(
        nullable = false
    )
    private String name;

    @Column(
        nullable = false
    )
    private String surname;

    @Column(
        nullable = false
    )
    private Date birth;
} 
