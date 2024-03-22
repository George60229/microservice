package com.example.jymapplication.response;

import com.example.jymapplication.model.TrainingType;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class TrainingResponse {
    String trainingName;
    Date trainingDate;
    TrainingType trainingType;
    int trainingDuration;

    String trainerName;
}
