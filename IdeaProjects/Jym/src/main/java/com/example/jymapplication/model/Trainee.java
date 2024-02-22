package com.example.jymapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Trainee extends User {
    private String dateOfBirth;
    private String address;


    @Override
    public String getClassName() {
        return "trainee";
    }

}
