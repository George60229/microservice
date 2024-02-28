package com.example.jymapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Training implements MyEntity {
    private Integer trainingId;
    private String trainingName;
    private TrainingType trainingType;
    private String trainingDate;
    private int trainingDuration;

    @Override
    public Integer getId() {
        return trainingId;
    }
}
