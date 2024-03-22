package com.example.jymapplication.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TraineeProfile {
    String firstName;
    String lastName;
    Date dateOfBirth;
    String address;
    boolean isActive;
    Set<TrainerInfo> trainers;
}
