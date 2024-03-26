package com.example.jymapplication.response;

import com.example.jymapplication.model.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class TrainerResponse {

    String firstName;
    String lastName;
    TrainingType specialization;
}
