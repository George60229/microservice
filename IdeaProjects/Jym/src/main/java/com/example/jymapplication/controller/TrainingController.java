package com.example.jymapplication.controller;

import com.example.jymapplication.request.TrainingRequest;
import com.example.jymapplication.service.TrainingService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/create")
    public ResponseEntity<String> addTraining(@RequestBody TrainingRequest trainingRequest) throws AccessDeniedException {
        if (userService.checkCredential(trainingRequest.getUserLoginRequest())) {
            trainingService.createTraining(trainingRequest);
            return ResponseEntity.ok("Created!");
        }
        throw new AccessDeniedException("Wrong Credential");
    }
}
