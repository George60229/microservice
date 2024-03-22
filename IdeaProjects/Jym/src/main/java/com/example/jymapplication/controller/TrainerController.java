package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.request.TrainerRequest;
import com.example.jymapplication.request.TrainingTrainerRequest;
import com.example.jymapplication.request.UserLoginRequest;
import com.example.jymapplication.response.TrainerProfile;
import com.example.jymapplication.response.TrainerResponse;
import com.example.jymapplication.response.TrainingResponse;
import com.example.jymapplication.service.TrainerService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    TrainerService trainerService;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public TrainerResponse registration(@RequestBody TrainerDto trainerDto) {
        return trainerService.createTrainer(trainerDto);
    }

    @PostMapping("/get/{username}")
    public TrainerProfile get(@PathVariable String username, @RequestBody UserLoginRequest userLoginRequest) throws AccessDeniedException {
        if (userService.checkCredential(userLoginRequest)) {
            return trainerService.selectTrainer(username);
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @PutMapping("/put")
    public TrainerProfile put(@RequestBody TrainerRequest trainerRequest) throws AccessDeniedException {
        if (userService.checkCredential(trainerRequest.getUserLoginRequest())) {
            return trainerService.updateTrainer(trainerRequest);
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @PatchMapping("/activate/{username}/{isActive}")
    public ResponseEntity<String> changeActivity(@PathVariable String username, @PathVariable boolean isActive
            , @RequestBody UserLoginRequest userLoginRequest) throws AccessDeniedException {
        if (userService.checkCredential(userLoginRequest)) {
            trainerService.changeActivity(username, isActive);
            return ResponseEntity.ok("Changed");
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @GetMapping("/getTraining")
    public Set<TrainingResponse> getTrainings(@RequestBody TrainingTrainerRequest trainingTrainerRequest,
                                              @RequestBody UserLoginRequest userLoginRequest) throws AccessDeniedException {
        if (userService.checkCredential(userLoginRequest)) {
            return trainerService.getTraining(trainingTrainerRequest);
        }
        throw new AccessDeniedException("Wrong Credential");
    }
}
