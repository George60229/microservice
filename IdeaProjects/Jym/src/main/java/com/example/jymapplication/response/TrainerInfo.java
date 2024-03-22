package com.example.jymapplication.response;

import com.example.jymapplication.model.TrainingType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrainerInfo {
    String firstName;
    String lastName;

    TrainingType specialization;

    boolean isActive;


}
