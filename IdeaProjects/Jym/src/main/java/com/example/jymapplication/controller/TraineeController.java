package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.request.TraineeUpdateDTO;
import com.example.jymapplication.request.TrainingTraineeDTO;
import com.example.jymapplication.request.UserLoginDTO;
import com.example.jymapplication.response.TraineeProfile;
import com.example.jymapplication.response.TraineeResponse;
import com.example.jymapplication.response.TrainerInfo;
import com.example.jymapplication.response.TrainingResponse;
import com.example.jymapplication.service.TraineeService;
import com.example.jymapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@RestController
@RequestMapping("/trainee")

public class TraineeController {
    TraineeService traineeService;
    UserService userService;

    public TraineeController(TraineeService traineeService, UserService userService) {
        this.traineeService = traineeService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public TraineeResponse registration(@RequestBody TraineeDto traineeDto) {
        return traineeService.createTrainee(traineeDto);
    }

    @GetMapping("/get/{id}")
    public TraineeProfile get(@PathVariable int id) {
        return traineeService.get(id);
    }
    static class UpdateAuthorize {
        TraineeUpdateDTO traineeUpdateDTO;
        UserLoginDTO userLoginDTO;
    }

    @PutMapping("/update")
    public TraineeProfile update(@RequestBody UpdateAuthorize request) {
        if (userService.checkCredential(request.userLoginDTO)) {
            return traineeService.editTrainee(request.traineeUpdateDTO);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id, @RequestBody UserLoginDTO userLoginDTO) {
        if (userService.checkCredential(userLoginDTO)) {
            traineeService.delete(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access is denied");
    }

    @GetMapping("/getFreeTrainers/{usernameToGet}")
    public Set<TrainerInfo> getTrainers(@PathVariable String usernameToGet, @RequestBody UserLoginDTO userLoginDTO) throws AccessDeniedException {
        if (userService.checkCredential(userLoginDTO)) {
            return traineeService.getFreeTrainers(usernameToGet);
        }
        return null;
    }

    @PatchMapping("/activate/{usernameToChange}/{isActive}")
    public ResponseEntity<String> changeActivity(@PathVariable String usernameToChange, @PathVariable boolean isActive,
                                                 @RequestBody UserLoginDTO userLoginDTO) {
        if (userService.checkCredential(userLoginDTO)) {
            userService.changeActivity(usernameToChange, isActive);
            return ResponseEntity.ok("Changed");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access is denied");
    }
    static class TrainersListAuthorize {
        Set<String> trainersUsername;
        UserLoginDTO userLoginDTO;
    }
    @PutMapping("/updateTrainers/{usernameToGet}")
    public Set<TrainerInfo> trainersList(@PathVariable String usernameToGet, @RequestBody TrainersListAuthorize trainersListAuthorize) {
        if (userService.checkCredential(trainersListAuthorize.userLoginDTO)) {
            return traineeService.updateTrainers(usernameToGet, trainersListAuthorize.trainersUsername);
        }
        return null;
    }

    static class TrainingTraineeAuthorize {
        TrainingTraineeDTO trainingTraineeDTO;
        UserLoginDTO userLoginDTO;
    }


    @GetMapping("/getTraining")
    public Set<TrainingResponse> getTraining(TrainingTraineeAuthorize trainingTraineeAuthorize) {
        if (userService.checkCredential(trainingTraineeAuthorize.userLoginDTO)) {
            return traineeService.getTraining(trainingTraineeAuthorize.trainingTraineeDTO);
        }
        return null;
    }
}
