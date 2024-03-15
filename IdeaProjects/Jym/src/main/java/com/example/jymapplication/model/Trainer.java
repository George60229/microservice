package com.example.jymapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Trainer extends MyUser {
    public Trainer(String firstName, String lastName, String specialization) {
        super(firstName, lastName);
        this.specialization = specialization;
    }

    private String specialization;

    @ManyToMany
    private Set<Training> training;

}
