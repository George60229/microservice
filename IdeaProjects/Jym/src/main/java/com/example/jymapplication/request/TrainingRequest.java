package com.example.jymapplication.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;
@Getter
@AllArgsConstructor
public class TrainingRequest {
    @NonNull
    UserLoginRequest userLoginRequest;
    String traineeUsername;
    String trainerUsername;
    String trainingName;
    Date TrainingDate;
    int trainingDuration;
}
