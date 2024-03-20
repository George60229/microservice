package com.example.jymapplication.comand;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.model.TrainingType;

import java.util.HashSet;
import java.util.Set;

public class TrainingTypeCommand implements Command {
    private final Set<Training> trainings;
    private final TrainingType trainingType;

    public TrainingTypeCommand(Set<Training> trainee, TrainingType trainingType) {
        this.trainings = trainee;
        this.trainingType = trainingType;
    }

    @Override
    public Set<Training> execute() {
        Set<Training> resultTrainings = new HashSet<>();
        for (Training training : trainings) {
            if (training.getTrainingType().equals(trainingType)) {
                resultTrainings.add(training);
            }
        }
        return resultTrainings;
    }
}
