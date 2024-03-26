package com.example.jymapplication.controller;

import com.example.jymapplication.model.TrainingType;
import com.example.jymapplication.service.TrainingTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class TrainingTypeControllerTest {

    @Mock
    private TrainingTypeService trainingTypeService;
    @InjectMocks
    private TrainingTypeController controller;

    private MockMvc mockMvc;

    @Test
    public void testGetAllTrainingTypes() throws Exception {
        List<TrainingType> trainingTypes = new ArrayList<>();
        TrainingType trainingType = new TrainingType();
        trainingType.setName("Type1");
        TrainingType trainingType2 = new TrainingType();
        trainingType2.setName("Type2");
        trainingTypes.add(trainingType);
        trainingTypes.add(trainingType2);
        when(trainingTypeService.getAll()).thenReturn(trainingTypes);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        RequestBuilder request = get("/trainingType/get");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Type1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Type2"));
    }
}
