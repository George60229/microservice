package com.example.jymapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Trainee extends User {
    private LocalDate dateOfBirth;
    private String address;

    public Trainee(String firstName, String lastName, String username, String password, boolean isActive, LocalDate dateOfBirth, String address) {
        super(firstName, lastName, username, password, isActive);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}
