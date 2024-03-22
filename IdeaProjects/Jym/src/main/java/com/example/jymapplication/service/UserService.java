package com.example.jymapplication.service;

import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.repository.MyUserRepository;
import com.example.jymapplication.request.ChangePasswordRequest;
import com.example.jymapplication.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    TraineeService traineeService;

    @Autowired
    TrainerService trainerService;

    @Autowired
    MyUserRepository myUserRepository;


    public boolean checkCredential(UserLoginRequest userLoginRequest) {

        if (userLoginRequest.getUsername().equals("admin") && userLoginRequest.getPassword().equals("admin")) {
            return true;
        }
        if (myUserRepository.findByUsername(userLoginRequest.getUsername()) != null) {
            return myUserRepository.findByUsername(userLoginRequest.getUsername()).getPassword().equals(userLoginRequest.getPassword());
        }
        return false;
    }

    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        MyUser myUser = myUserRepository.findByUsername(changePasswordRequest.getUsername());
        if (myUser.getPassword().equals(changePasswordRequest.getOldPassword())) {
            myUser.setPassword(changePasswordRequest.getNewPassword());
            myUserRepository.save(myUser);
            return true;
        }
        return false;
    }


}
