package com.example.jymapplication.utils;

import com.example.jymapplication.dto.TraineeDto;
import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import org.springframework.stereotype.Service;

@Service
public class Converter {
    public Trainee traineeDtoToModel(TraineeDto dto) {
        if (dto.getDateOfBirth() == null || dto.getAddress() == null || dto.getFirstName() == null || dto.getLastName() == null) {
            return null;
        }
        return new Trainee(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getAddress());
    }

    public Trainer trainerDtoToModel(TrainerDto dto) {
        if (dto.getFirstName() == null || dto.getLastName() == null || dto.getSpecialization() == null) {
            return null;
        }
        return new Trainer(dto.getFirstName(), dto.getLastName(), dto.getSpecialization());
    }
}
