package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.request.TrainerUpdateDTO;
import com.example.jymapplication.request.TrainingTraineeDTO;
import com.example.jymapplication.request.TrainingTrainerDTO;
import com.example.jymapplication.request.UserLoginDTO;
import com.example.jymapplication.response.TrainerProfile;
import com.example.jymapplication.response.TrainerResponse;
import com.example.jymapplication.response.TrainingResponse;
import com.example.jymapplication.service.TrainerService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

    public TrainerController(TrainerService trainerService, UserService userService) {
        this.trainerService = trainerService;
        this.userService = userService;
    }

    TrainerService trainerService;
    UserService userService;

    @PostMapping("/create")
    public TrainerResponse registration(@RequestBody TrainerDto trainerDto) {
        return trainerService.createTrainer(trainerDto);
    }

    @GetMapping("/get/{usernameToGet}")
    public TrainerProfile get(@PathVariable String usernameToGet) {
        return trainerService.selectTrainer(usernameToGet);
    }

    static class TrainerAuthorize {
        TrainerUpdateDTO trainingTraineeDTO;
        UserLoginDTO userLoginDTO;
    }

    @PutMapping("/put")
    public TrainerProfile put(@RequestBody TrainerAuthorize trainerAuthorize) {
        if (userService.checkCredential(trainerAuthorize.userLoginDTO)) {
            return trainerService.updateTrainer(trainerAuthorize.trainingTraineeDTO);
        }
        return null;
    }

    @PatchMapping("/activate/{usernameToGet}/{isActive}")
    public ResponseEntity<String> changeActivity(@PathVariable String usernameToGet,
                                                 @PathVariable boolean isActive,
                                                 @RequestBody UserLoginDTO userLoginDTO) {
        if (userService.checkCredential(userLoginDTO)) {
            userService.changeActivity(usernameToGet, isActive);
            return ResponseEntity.ok("Changed");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access is denied");
    }

    static class TrainingAuthorize {
        TrainingTrainerDTO trainingTrainerDTO;
        UserLoginDTO userLoginDTO;
    }

    @GetMapping("/getTraining")
    public Set<TrainingResponse> getTrainings(@RequestBody TrainingAuthorize trainingAuthorize) throws AccessDeniedException {
        if (userService.checkCredential(trainingAuthorize.userLoginDTO)) {
            return trainerService.getTraining(trainingAuthorize.trainingTrainerDTO);
        }
        return null;
    }
}
