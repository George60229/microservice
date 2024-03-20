package com.example.jymapplication.comand;

import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;

import java.util.HashSet;
import java.util.Set;

public class TraineeNameCommand implements Command {
    private final Set<Training> trainings;
    private final String traineeName;

    public TraineeNameCommand(Set<Training> trainings, String traineeName) {
        this.trainings = trainings;
        this.traineeName = traineeName;
    }

    @Override
    public Set<Training> execute() {
        Set<Training> resultTrainings = new HashSet<>();
        for (Training training : trainings) {
            if (training.getTrainer().getFirstName().equals(traineeName)) {
                resultTrainings.add(training);
            }
        }
        return resultTrainings;
    }
}
