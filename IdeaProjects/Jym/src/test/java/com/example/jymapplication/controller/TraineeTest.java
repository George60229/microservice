package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.request.UserLoginDTO;
import com.example.jymapplication.response.TraineeProfile;
import com.example.jymapplication.response.TraineeResponse;
import com.example.jymapplication.service.TraineeService;
import com.example.jymapplication.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@WebMvcTest(TraineeController.class)
public class TraineeTest {

    @InjectMocks
    private TraineeController myController;

    @MockBean
    private TraineeService myService;

    @MockBean
    private UserService userService;

    UserLoginDTO userLoginDTO = new UserLoginDTO("admin", "admin");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myController = new TraineeController(myService, userService);

    }

    @Test
    public void testGetById() {
        TraineeProfile trainee = new TraineeProfile();
        trainee.setFirstName("George");
        Mockito.when(this.userService.checkCredential(userLoginDTO)).thenReturn(true);
        Mockito.when(this.myService.get(1)).thenReturn(trainee);
        TraineeProfile traineeProfile = myController.get(1);
        Assertions.assertEquals(traineeProfile.getFirstName(), "George");
    }

    //todo tests
    @Test
    public void testCreate() {
        TraineeDto trainee = new TraineeDto();
        trainee.setFirstName("George");
        TraineeResponse traineeResponse = new TraineeResponse();
        traineeResponse.setUsername("Start");
        when(myService.createTrainee(trainee)).thenReturn(traineeResponse);
        when(userService.checkCredential(userLoginDTO)).thenReturn(true);
        TraineeResponse traineeProfile = myController.registration(trainee);
        Assertions.assertEquals(traineeProfile.getUsername(), "Start");
    }
}

