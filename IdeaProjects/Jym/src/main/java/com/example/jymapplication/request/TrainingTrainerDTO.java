package com.example.jymapplication.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TrainingTrainerDTO {

    @NonNull
    String username;
    Date periodFrom;
    Date periodTo;
    String traineeName;

}
