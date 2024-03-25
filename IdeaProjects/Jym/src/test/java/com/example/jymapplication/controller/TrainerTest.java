package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.response.TrainerProfile;
import com.example.jymapplication.response.TrainerResponse;
import com.example.jymapplication.service.TrainerService;
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

@WebMvcTest(TrainerController.class)
public class TrainerTest {

    @InjectMocks
    private TrainerController myController;

    @MockBean
    private TrainerService myService;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myController = new TrainerController(myService, userService);

    }

    @Test
    public void testGetById() {
        TrainerProfile trainee = new TrainerProfile();
        trainee.setFirstName("George");
        Mockito.when(this.myService.selectTrainer("George")).thenReturn(trainee);
        TrainerProfile traineeProfile = myController.get("George");
        Assertions.assertEquals(traineeProfile.getFirstName(), "George");
    }

    @Test
    public void testCreate() {
        TrainerDto trainee = new TrainerDto();
        trainee.setFirstName("George");
        TrainerResponse traineeResponse = new TrainerResponse();
        traineeResponse.setFirstName("Start");
        when(myService.createTrainer(trainee)).thenReturn(traineeResponse);
        TrainerResponse traineeProfile = myController.registration(trainee);
        Assertions.assertEquals(traineeProfile.getFirstName(), "Start");
    }
}
