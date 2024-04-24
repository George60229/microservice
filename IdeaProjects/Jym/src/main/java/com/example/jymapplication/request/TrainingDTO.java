package com.example.jymapplication.request;

import com.example.jymapplication.dto.ActionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
public class TrainingDTO {

    String traineeUsername;
    String trainerUsername;
    String trainingName;

    LocalDate TrainingDate = LocalDate.now();
    int trainingDuration;

    ActionType actionType;
}
