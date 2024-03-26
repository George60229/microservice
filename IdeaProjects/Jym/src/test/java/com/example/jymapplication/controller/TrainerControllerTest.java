package com.example.jymapplication.controller;

import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.response.TrainerProfile;
import com.example.jymapplication.response.TrainerResponse;
import com.example.jymapplication.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TrainerControllerTest {
    @Mock
    private TrainerService trainerService;
    @InjectMocks
    private TrainerController controller;
    private MockMvc mockMvc;

    @Test
    public void testGetById() throws Exception {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setFirstName("exampleUsername");
        TrainerResponse trainerResponse = new TrainerResponse();
        when(trainerService.createTrainer(any(TrainerDto.class))).thenReturn(trainerResponse);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        RequestBuilder request = post("/trainer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"exampleUsername\"}");
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        TrainerProfile trainerProfile = new TrainerProfile();
        trainerProfile.setUsername("exampleUsername");
        when(trainerService.selectTrainer("exampleUsername")).thenReturn(trainerProfile);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/trainer/get/exampleUsername"))
                .andExpect(status().isOk());
    }
}
