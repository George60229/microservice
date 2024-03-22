package com.example.jymapplication.controller;

import com.example.jymapplication.model.TrainingType;
import com.example.jymapplication.request.UserLoginRequest;
import com.example.jymapplication.service.TrainingTypeService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/trainingType")
public class TrainingTypeController {
    @Autowired
    TrainingTypeService trainingTypeService;
    @Autowired
    UserService userService;

    @GetMapping("/get")
    public List<TrainingType> get(UserLoginRequest userLoginRequest) throws AccessDeniedException {
        if (userService.checkCredential(userLoginRequest)) {
            return trainingTypeService.getAll();
        }
        throw new AccessDeniedException("Wrong Credential");
    }

}
