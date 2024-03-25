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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
//todo inner classes
    @PutMapping("/update")
    public TraineeProfile update(@RequestBody TraineeUpdateDTO request, @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            return traineeService.editTrainee(request);
        }
        throw new AccessDeniedException("Wrong Credential");//todo fix exceptionHandler
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id, @RequestParam String username, @RequestParam String password) {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            traineeService.delete(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/getFreeTrainers/{usernameToGet}")
    public Set<TrainerInfo> getTrainers(@PathVariable String usernameToGet, @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            return traineeService.getFreeTrainers(usernameToGet);
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @PatchMapping("/activate/{usernameToChange}/{isActive}")
    public ResponseEntity<String> changeActivity(@PathVariable String usernameToChange, @PathVariable boolean isActive,
                                                 @RequestParam String username, @RequestParam String password) {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            traineeService.changeActivity(usernameToChange, isActive);
        }
        return ResponseEntity.ok("Changed");
    }

    @PutMapping("/updateTrainers/{usernameToGet}")
    public Set<TrainerInfo> trainersList(@PathVariable String usernameToGet, @RequestBody Set<String> trainersUsername, @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            return traineeService.updateTrainers(usernameToGet, trainersUsername);
        }
        throw new AccessDeniedException("Wrong Credential");

    }

    @GetMapping("/getTraining")
    public Set<TrainingResponse> getTraining(@RequestBody TrainingTraineeDTO trainingTraineeDTO, @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            return traineeService.getTraining(trainingTraineeDTO);
        }
        throw new AccessDeniedException("Wrong Credential");
    }
}
