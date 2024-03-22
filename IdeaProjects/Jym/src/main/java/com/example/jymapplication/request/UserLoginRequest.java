package com.example.jymapplication.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class UserLoginRequest {
    @NonNull
    String username;
    @NonNull
    String password;
}
