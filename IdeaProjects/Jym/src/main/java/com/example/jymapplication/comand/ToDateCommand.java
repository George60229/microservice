package com.example.jymapplication.comand;

import com.example.jymapplication.model.Training;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ToDateCommand implements Command {
    private final Set<Training> trainings;
    private final LocalDate toDate;

    public ToDateCommand(Set<Training> trainings, LocalDate toDate) {
        this.trainings = trainings;
        this.toDate = toDate;
    }

    @Override
    public Set<Training> execute() {
        Set<Training> resultTrainings = new HashSet<>();
        for (Training training : trainings) {
            if (training.getTrainingDate().isBefore(toDate)) {
                resultTrainings.add(training);
            }
        }
        return resultTrainings;
    }
}

