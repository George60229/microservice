package com.example.jymapplication.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToMany(mappedBy = "trainees",cascade = CascadeType.ALL)
    private Set<Training> trainings;
}
