package com.example.jymapplication.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TrainingDTO {

    String traineeUsername;
    String trainerUsername;
    String trainingName;
    Date TrainingDate;
    int trainingDuration;
}
