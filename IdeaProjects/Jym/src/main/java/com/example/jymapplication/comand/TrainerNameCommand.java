package com.example.jymapplication.comand;

import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Training;

import java.util.HashSet;
import java.util.Set;

public class TrainerNameCommand implements Command {
    private final Set<Training> trainings;
    private final String trainerName;

    public TrainerNameCommand(Set<Training> trainings, String trainerName) {
        this.trainings = trainings;
        this.trainerName = trainerName;
    }

    @Override
    public Set<Training> execute() {
        Set<Training> resultTrainings = new HashSet<>();
        for (Training training : trainings) {
            if (training.getTrainer().getFirstName().equals(trainerName)) {
                resultTrainings.add(training);
            }
        }
        return resultTrainings;
    }
}
