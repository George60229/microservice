package com.example.jymapplication.dto;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class TraineeDto {

    @NonNull
    String firstName;
    @NonNull
    String lastName;
    Date dateOfBirth;
    String address;



}
