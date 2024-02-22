package com.example.jymapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class Trainer extends User {
    private String specialization;




    @Override
    public String getClassName() {
        return "trainer";
    }
}
