package com.example.jymapplication.response;

import com.example.jymapplication.model.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainerProfile {
    String firstName;
    String lastName;
    String username;
    TrainingType specialization;
    boolean isActive;
    Set<TraineeInfo> trainees;
}
