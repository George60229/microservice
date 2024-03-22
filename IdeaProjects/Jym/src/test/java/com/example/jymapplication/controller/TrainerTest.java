package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.request.UserLoginRequest;
import com.example.jymapplication.response.TrainerProfile;
import com.example.jymapplication.response.TrainerResponse;
import com.example.jymapplication.service.TrainerService;
import com.example.jymapplication.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.AccessDeniedException;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TrainerTest {

    @InjectMocks
    private TrainerController myController;

    @Mock
    private TrainerService myService;

    @Mock
    private UserService userService;

    UserLoginRequest userLoginRequest = new UserLoginRequest("admin", "admin");

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() throws AccessDeniedException {
        TrainerProfile trainee = new TrainerProfile();
        trainee.setFirstName("George");
        when(myService.selectTrainer("George")).thenReturn(trainee);
        when(userService.checkCredential(userLoginRequest)).thenReturn(true);
        TrainerProfile traineeProfile = myController.get("George", userLoginRequest);
        Assertions.assertEquals(traineeProfile.getFirstName(), "George");
    }

    @Test
    public void testCreate() {
        TrainerDto trainee = new TrainerDto();
        trainee.setFirstName("George");
        TrainerResponse traineeResponse = new TrainerResponse();
        traineeResponse.setFirstName("Start");
        when(myService.createTrainer(trainee)).thenReturn(traineeResponse);
        when(userService.checkCredential(userLoginRequest)).thenReturn(true);
        TrainerResponse traineeProfile = myController.registration(trainee);
        Assertions.assertEquals(traineeProfile.getFirstName(), "Start");
    }


}
