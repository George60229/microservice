package com.example.jymapplication.dto;

import com.example.jymapplication.model.TrainingType;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    private TrainingType specialization;
}
