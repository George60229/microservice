package com.example.jymapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Training implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String trainingName;


    @ManyToOne
    @JoinColumn(name = "training_type_id")
    private TrainingType trainingType;
    private Date trainingDate;
    private int trainingDuration;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToMany
    @JoinTable(name = "training_trainee",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id"))
    private Set<Trainee> trainees;

}
