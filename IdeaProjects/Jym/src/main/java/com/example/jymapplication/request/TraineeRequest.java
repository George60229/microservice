package com.example.jymapplication.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@AllArgsConstructor
@Getter
public class TraineeRequest {
    @NonNull
    UserLoginRequest userLoginRequest;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String username;
    Date dateOfBirth;

    String address;
    @NonNull
    Boolean isActive;

}
