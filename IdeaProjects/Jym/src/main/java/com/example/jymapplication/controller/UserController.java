package com.example.jymapplication.controller;

import com.example.jymapplication.request.ChangePasswordRequest;
import com.example.jymapplication.request.UserLoginRequest;
import com.example.jymapplication.service.TraineeService;
import com.example.jymapplication.service.TrainerService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    TraineeService traineeService;

    @Autowired
    TrainerService trainerService;

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {

        if (!userService.checkCredential(userLoginRequest)) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("WrongCredential");
        }
        return ResponseEntity.ok("Success!");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        if (userService.checkCredential(changePasswordRequest.getUserLoginRequest())) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("WrongCredential");
        }
        userService.changePassword(changePasswordRequest);
        return ResponseEntity.ok("Success!");
    }
}
