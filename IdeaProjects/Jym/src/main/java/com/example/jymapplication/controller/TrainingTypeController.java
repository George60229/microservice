package com.example.jymapplication.controller;

import com.example.jymapplication.model.TrainingType;
import com.example.jymapplication.request.UserLoginDTO;
import com.example.jymapplication.service.TrainingTypeService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/trainingType")
public class TrainingTypeController {

    public TrainingTypeController(TrainingTypeService trainingTypeService, UserService userService) {
        this.trainingTypeService = trainingTypeService;
        this.userService = userService;
    }

    TrainingTypeService trainingTypeService;

    UserService userService;

    @GetMapping("/get")
    public List<TrainingType> get() {
        return trainingTypeService.getAll();
    }

}
