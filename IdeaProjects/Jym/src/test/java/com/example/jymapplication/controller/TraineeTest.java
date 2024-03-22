package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.request.UserLoginRequest;
import com.example.jymapplication.response.TraineeProfile;
import com.example.jymapplication.response.TraineeResponse;
import com.example.jymapplication.service.TraineeService;
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
public class TraineeTest {

    @InjectMocks
    private TraineeController myController;

    @Mock
    private TraineeService myService;

    @Mock
    private UserService userService;

    UserLoginRequest userLoginRequest = new UserLoginRequest("admin", "admin");

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() throws AccessDeniedException {
        TraineeProfile trainee = new TraineeProfile();
        trainee.setFirstName("George");
        when(myService.get(1)).thenReturn(trainee);
        when(userService.checkCredential(userLoginRequest)).thenReturn(true);
        TraineeProfile traineeProfile = myController.get(1, userLoginRequest);
        Assertions.assertEquals(traineeProfile.getFirstName(), "George");
    }
    @Test
    public void testCreate() {
        TraineeDto trainee = new TraineeDto();
        trainee.setFirstName("George");
        TraineeResponse traineeResponse = new TraineeResponse();
        traineeResponse.setUsername("Start");
        when(myService.createTrainee(trainee)).thenReturn(traineeResponse);
        when(userService.checkCredential(userLoginRequest)).thenReturn(true);
        TraineeResponse traineeProfile = myController.registration(trainee);
        Assertions.assertEquals(traineeProfile.getUsername(), "Start");
    }
}

