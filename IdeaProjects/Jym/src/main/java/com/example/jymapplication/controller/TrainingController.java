package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerRequest;
import com.example.jymapplication.openfeign.AnotherMicroserviceClient;
import com.example.jymapplication.request.TrainingDTO;
import com.example.jymapplication.service.TrainingService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AnotherMicroserviceClient anotherMicroserviceClient;

    @ExceptionHandler(AccessDeniedException.class)
    @PostMapping("/create")
    public ResponseEntity<String> addTraining(@RequestBody TrainingDTO trainingAuthorize) {
        TrainerRequest trainerRequest = trainingService.getTrainerRequest(trainingAuthorize);
        anotherMicroserviceClient.saveInfo(trainerRequest);
        return ResponseEntity.ok(trainingService.createTraining(trainingAuthorize).toString());
    }
}
