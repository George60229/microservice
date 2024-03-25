package com.example.jymapplication.controller;

import com.example.jymapplication.request.TrainingDTO;
import com.example.jymapplication.request.UserLoginDTO;
import com.example.jymapplication.service.TrainingService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.AccessDeniedException;

@Controller
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    UserService userService;
    @Autowired
    TrainingService trainingService;

    static class TrainingAuthorize {
        TrainingDTO trainingTrainerDTO;
        UserLoginDTO userLoginDTO;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @PostMapping("/create")
    public ResponseEntity<String> addTraining(@RequestBody TrainingAuthorize trainingAuthorize) throws AccessDeniedException {

        if (userService.checkCredential(trainingAuthorize.userLoginDTO)) {
            trainingService.createTraining(trainingAuthorize.trainingTrainerDTO);
            return ResponseEntity.ok("Created!");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access is denied");
    }
}
