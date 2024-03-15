package com.example.jymapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Trainee extends MyUser {


    private Date dateOfBirth;
    private String address;

    public Trainee(String firstName, String secondName, Date dateOfBirth, String address) {
        super(firstName, secondName);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


    @ManyToMany(mappedBy = "trainees")
    private Set<Training> trainings;
}
