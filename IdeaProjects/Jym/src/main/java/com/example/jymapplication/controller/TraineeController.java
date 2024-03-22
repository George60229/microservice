package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.request.TraineeRequest;
import com.example.jymapplication.request.TrainingTraineeRequest;
import com.example.jymapplication.request.UserLoginRequest;
import com.example.jymapplication.response.TraineeProfile;
import com.example.jymapplication.response.TraineeResponse;
import com.example.jymapplication.response.TrainerInfo;
import com.example.jymapplication.response.TrainingResponse;
import com.example.jymapplication.service.TraineeService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    TraineeService traineeService;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public TraineeResponse registration(@RequestBody TraineeDto traineeDto) {
        return traineeService.createTrainee(traineeDto);
    }

    @GetMapping("/get/{id}")
    public TraineeProfile get(@PathVariable int id, @RequestBody UserLoginRequest userLoginRequest) throws AccessDeniedException {
        if (userService.checkCredential(userLoginRequest)) {
            return traineeService.get(id);
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @PutMapping("/update")
    public TraineeProfile update(@RequestBody TraineeRequest request) throws AccessDeniedException {
        if (userService.checkCredential(request.getUserLoginRequest())) {
            return traineeService.editTrainee(request);
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id, @RequestBody UserLoginRequest userLoginRequest) {
        if (userService.checkCredential(userLoginRequest)) {
            traineeService.delete(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/getFreeTrainers/{username}")
    public Set<TrainerInfo> getTrainers(@PathVariable String username, @RequestBody UserLoginRequest userLoginRequest) throws AccessDeniedException {
        if (userService.checkCredential(userLoginRequest)) {
            return traineeService.getFreeTrainers(username);
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @PatchMapping("/activate/{username}/{isActive}")
    public ResponseEntity<String> changeActivity(@PathVariable String username, @PathVariable boolean isActive,
                                                 @RequestBody UserLoginRequest userLoginRequest) {
        if (userService.checkCredential(userLoginRequest)) {
            traineeService.changeActivity(username, isActive);
        }
        return ResponseEntity.ok("Changed");
    }

    @PutMapping("/updateTrainers/{username}")
    public Set<TrainerInfo> trainersList(@PathVariable String username, @RequestBody Set<String> trainersUsername) {
        return traineeService.updateTrainers(username, trainersUsername);
    }

    @GetMapping("/getTraining")
    public Set<TrainingResponse> getTraining(@RequestBody TrainingTraineeRequest trainingTraineeRequest) throws AccessDeniedException {
        if (userService.checkCredential(trainingTraineeRequest.getUserLoginRequest())) {
            return traineeService.getTraining(trainingTraineeRequest);
        }
        throw new AccessDeniedException("Wrong Credential");
    }
}
