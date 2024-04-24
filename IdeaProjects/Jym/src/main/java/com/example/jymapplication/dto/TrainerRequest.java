package com.example.jymapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TrainerRequest {
    String username;
    String trainerFirstName;
    String trainerLastName;
    boolean isActive;
    LocalDate trainingDate;
    int trainingDuration;
    ActionType actionType;
}
