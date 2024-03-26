package com.example.jymapplication.controller;

import com.example.jymapplication.request.ChangePasswordDTO;
import com.example.jymapplication.request.TrainingTraineeDTO;
import com.example.jymapplication.request.UserLoginDTO;
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
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {

        if (!userService.checkCredential(userLoginDTO)) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("WrongCredential");
        }
        return ResponseEntity.ok("Success!");
    }

    static class ChangePassAndAuthorize {
        ChangePasswordDTO changePasswordDTO;
        UserLoginDTO userLoginDTO;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassAndAuthorize changePassAndAuthorize) {
        if (userService.checkCredential(changePassAndAuthorize.userLoginDTO)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("WrongCredential");
        }
        userService.changePassword(changePassAndAuthorize.changePasswordDTO);
        return ResponseEntity.ok("Success!");
    }
}
