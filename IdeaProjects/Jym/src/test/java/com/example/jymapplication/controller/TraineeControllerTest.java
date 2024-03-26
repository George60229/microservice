package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.response.TraineeProfile;
import com.example.jymapplication.response.TraineeResponse;
import com.example.jymapplication.service.TraineeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TraineeControllerTest {

    @Mock
    private TraineeService traineeService;

    @InjectMocks
    private TraineeController controller;

    private MockMvc mockMvc;

    @Test
    public void testRegistration() throws Exception {
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setFirstName("John");
        traineeDto.setLastName("Doe");
        TraineeResponse traineeResponse = new TraineeResponse();
        traineeResponse.setUsername("JohnDoe");
        when(traineeService.createTrainee(any(TraineeDto.class))).thenReturn(traineeResponse);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        RequestBuilder request = post("/trainee/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"John Doe\"}");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"username\": \"JohnDoe\" }"));
    }

    @Test
    public void testGetTraineeById() throws Exception {
        TraineeDto traineeProfile = new TraineeDto();
        traineeProfile.setFirstName("John");
        traineeProfile.setLastName("Doe");
        TraineeProfile traineeResponse = new TraineeProfile();
        traineeResponse.setAddress("st1");
        when(traineeService.get(1)).thenReturn(traineeResponse);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/trainee/get/1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.address").value("st1"));
    }
}


