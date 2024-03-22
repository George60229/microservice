package com.example.jymapplication.request;

import com.example.jymapplication.model.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class TrainerRequest {
    @NonNull
    UserLoginRequest userLoginRequest;
    @NonNull
    String username;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    TrainingType specialization;
    @NonNull
    Boolean isActive;
}
