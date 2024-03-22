package com.example.jymapplication.dto;

import com.example.jymapplication.model.TrainingType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    private TrainingType specialization;
}
