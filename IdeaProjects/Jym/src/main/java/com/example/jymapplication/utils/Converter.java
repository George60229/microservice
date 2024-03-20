package com.example.jymapplication.utils;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import org.springframework.stereotype.Service;

@Service
public class Converter {
    public Trainee traineeDtoToModel(TraineeDto dto) {
        return (dto.getDateOfBirth() == null || dto.getAddress() == null ||
                dto.getFirstName() == null || dto.getLastName() == null) ? null :
                new Trainee(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getAddress());
    }

    public Trainer trainerDtoToModel(TrainerDto dto) {
        return (dto.getFirstName() == null || dto.getLastName() == null || dto.getSpecialization() == null) ? null :
                new Trainer(dto.getFirstName(), dto.getLastName(), dto.getSpecialization());
    }
}
