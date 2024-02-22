package com.example.jymapplication.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User implements Entity {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;


    private Integer userId;






}
