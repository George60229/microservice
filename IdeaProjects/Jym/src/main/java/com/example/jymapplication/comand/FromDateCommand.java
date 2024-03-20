package com.example.jymapplication.comand;

import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Training;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FromDateCommand implements Command {
    private Set<Training> trainings;
    private final Date fromDate;

    public FromDateCommand(Set<Training> trainings, Date fromDate) {
        this.trainings = trainings;
        this.fromDate = fromDate;
    }

    @Override
    public Set<Training> execute() {
        Set<Training> resultTrainings = new HashSet<>();
        for (Training training : trainings) {
            if (training.getTrainingDate().after(fromDate)) {
                resultTrainings.add(training);
            }
        }
        return resultTrainings;
    }
}
