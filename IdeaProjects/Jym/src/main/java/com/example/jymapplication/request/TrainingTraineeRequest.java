package com.example.jymapplication.request;

import com.example.jymapplication.model.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TrainingTraineeRequest {
    @NonNull
    UserLoginRequest userLoginRequest;
    @NonNull
    String username;
    Date periodFrom;
    Date periodTo;
    String trainerName;
    TrainingType trainingType;
}
