package com.example.jymapplication.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ChangePasswordRequest {
    @NonNull
    UserLoginRequest userLoginRequest;

    @NonNull
    String username;
    @NonNull

    String oldPassword;
    @NonNull
    String newPassword;
}
