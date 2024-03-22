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
    public Trainer(String firstName, String lastName, TrainingType specialization) {
        super(firstName, lastName);
        this.specialization = specialization;
    }

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private TrainingType specialization;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Training> trainings;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Trainee> trainees;

}
