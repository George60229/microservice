package com.example.jymapplication.controller;

import com.example.jymapplication.request.ChangePasswordDTO;
import com.example.jymapplication.request.UserLoginDTO;
import com.example.jymapplication.response.UserResponse;
import com.example.jymapplication.security.JwtUtil;
import com.example.jymapplication.service.TraineeService;
import com.example.jymapplication.service.TrainerService;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    TraineeService traineeService;
    @Autowired
    TrainerService trainerService;
    @Autowired
    UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginDTO userLoginDTO) {

        if (!userService.getByUsername(userLoginDTO.getUsername()).getIsActive()) {
            throw new BadCredentialsException("User is blocked");
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));
        String username = authentication.getName();
        UserResponse user = new UserResponse();
        String token = jwtUtil.createToken(user);
        UserResponse loginRes = new UserResponse();
        loginRes.setUsername(username);
        loginRes.setToken(token);
        return ResponseEntity.ok(loginRes);

    }

    static class ChangePassAndAuthorize {
        ChangePasswordDTO changePasswordDTO;
        UserLoginDTO userLoginDTO;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassAndAuthorize changePassAndAuthorize) {
        if (userService.checkCredential(changePassAndAuthorize.userLoginDTO)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("WrongCredential");
        }
        userService.changePassword(changePassAndAuthorize.changePasswordDTO);
        return ResponseEntity.ok("Success!");
    }
}
