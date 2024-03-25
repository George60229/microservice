package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.request.TrainerUpdateDTO;
import com.example.jymapplication.request.TrainingTrainerDTO;
import com.example.jymapplication.request.UserLoginDTO;
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

    @PostMapping("/get/{usernameToGet}")
    public TrainerProfile get(@PathVariable String usernameToGet) {
        return trainerService.selectTrainer(usernameToGet);
    }

    @PutMapping("/put")
    public TrainerProfile put(@RequestBody TrainerUpdateDTO trainerUpdateDTO, @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            return trainerService.updateTrainer(trainerUpdateDTO);
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @PatchMapping("/activate/{usernameToGet}/{isActive}")
    public ResponseEntity<String> changeActivity(@PathVariable String usernameToGet, @PathVariable boolean isActive
            , @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            trainerService.changeActivity(usernameToGet, isActive);
            return ResponseEntity.ok("Changed");
        }
        throw new AccessDeniedException("Wrong Credential");
    }

    @GetMapping("/getTraining")
    public Set<TrainingResponse> getTrainings(@RequestBody TrainingTrainerDTO trainingTrainerDTO
            , @RequestParam String username, @RequestParam String password) throws AccessDeniedException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        if (userService.checkCredential(userLoginDTO)) {
            return trainerService.getTraining(trainingTrainerDTO);
        }
        throw new AccessDeniedException("Wrong Credential");
    }
}
