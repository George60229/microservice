package com.example.jymapplication.service;

import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.repository.TraineeRepository;
import com.example.jymapplication.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class UserService {

    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    TraineeRepository traineeRepository;

    public String generateUsername(MyUser myEntity) {
        int counter = 0;
        if (myEntity instanceof Trainer) {
            List<Trainer> users = trainerRepository.findAll();
            for (Trainer trainer : users) {
                if (trainer.getFirstName().equals((myEntity).getFirstName())
                        && trainer.getLastName().equals((myEntity).getLastName())) {
                    counter++;
                }
            }
        }
        if (myEntity instanceof Trainee) {
            List<Trainee> users = traineeRepository.findAll();
            for (Trainee trainee : users) {
                if (trainee.getFirstName().equals((myEntity).getFirstName())
                        && trainee.getLastName().equals((myEntity).getLastName())) {
                    counter++;
                }
            }
        }
        if (counter == 0) {
            return myEntity.getFirstName() + "_" + (myEntity).getLastName();
        } else {
            return myEntity.getFirstName() + "_" + (myEntity).getLastName() + counter;
        }
    }

    public String generatePassword() {
        int minCharCode = 'a';
        int maxCharCode = 'z';
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(Character.toString(random.nextInt(maxCharCode - minCharCode + 1) + minCharCode));
        }
        return stringBuilder.toString();
    }
}
