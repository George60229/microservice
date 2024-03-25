package com.example.jymapplication.controller;

import com.example.jymapplication.request.TrainingDTO;
import com.example.jymapplication.request.UserLoginDTO;
import com.example.jymapplication.service.TrainingService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;

@Controller
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    UserService userService;
    @Autowired
    TrainingService trainingService;

    @PostMapping("/create")
    public ResponseEntity<String> addTraining(@RequestBody TrainingDTO trainingDTO, @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            trainingService.createTraining(trainingDTO);
            return ResponseEntity.ok("Created!");
        }
        throw new AccessDeniedException("Wrong Credential");
    }
}
