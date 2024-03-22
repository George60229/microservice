package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.model.TrainingType;
import com.example.jymapplication.request.UserLoginRequest;
import com.example.jymapplication.response.TrainerProfile;
import com.example.jymapplication.response.TrainerResponse;
import com.example.jymapplication.service.TrainerService;
import com.example.jymapplication.service.TrainingService;
import com.example.jymapplication.service.TrainingTypeService;
import com.example.jymapplication.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TrainingTypeTest {

    @InjectMocks
    private TrainingTypeController myController;

    @Mock
    private TrainingTypeService myService;

    @Mock
    private UserService userService;


    UserLoginRequest userLoginRequest = new UserLoginRequest("george", "123");

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() throws AccessDeniedException {
        TrainingType trainingType = new TrainingType();
        trainingType.setName("run");
        List<TrainingType> trainingTypes1 = new ArrayList<>();
        trainingTypes1.add(trainingType);
        when(myService.getAll()).thenReturn(trainingTypes1);
        when(userService.checkCredential(userLoginRequest)).thenReturn(true);

        List<TrainingType> trainingTypes = myController.get(userLoginRequest);
        Assertions.assertEquals(trainingTypes.get(0).getName(), "run");
    }


}
