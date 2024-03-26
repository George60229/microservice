package com.example.jymapplication.service;

import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.repository.MyUserRepository;
import com.example.jymapplication.request.ChangePasswordDTO;
import com.example.jymapplication.request.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    MyUserRepository myUserRepository;

    public UserService(TraineeService traineeService, TrainerService trainerService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    TraineeService traineeService;
    TrainerService trainerService;

    public boolean checkCredential(UserLoginDTO userLoginDTO) {
        if (myUserRepository.findByUsername(userLoginDTO.getUsername()) != null) {
            return myUserRepository.findByUsername(userLoginDTO.getUsername()).getPassword().equals(userLoginDTO.getPassword());
        }
        return false;
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        MyUser myUser = myUserRepository.findByUsername(changePasswordDTO.getUsername());
        if (myUser.getPassword().equals(changePasswordDTO.getOldPassword())) {
            myUser.setPassword(changePasswordDTO.getNewPassword());
            myUserRepository.save(myUser);
        }
    }
}
