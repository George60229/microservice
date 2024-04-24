package com.example.jymapplication.response;

import com.example.jymapplication.model.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
public class TrainingResponse {
    String trainingName;
    LocalDate trainingDate;
    TrainingType trainingType;
    int trainingDuration;
    String trainerName;
}
