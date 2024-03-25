package com.example.jymapplication.controller;

import com.example.jymapplication.model.TrainingType;
import com.example.jymapplication.service.TrainingTypeService;
import com.example.jymapplication.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(TrainingType.class)
public class TrainingTypeTest {

    @InjectMocks
    private TrainingTypeController myController;

    @MockBean
    private TrainingTypeService myService;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myController = new TrainingTypeController(myService, userService);
    }

    @Test
    public void testGetById() {
        TrainingType trainingType = new TrainingType();
        trainingType.setName("run");
        List<TrainingType> trainingTypes1 = new ArrayList<>();
        trainingTypes1.add(trainingType);
        when(myService.getAll()).thenReturn(trainingTypes1);
        List<TrainingType> trainingTypes = myController.get();
        Assertions.assertEquals(trainingTypes.get(0).getName(), "run");
    }
}
