package com.example.jymapplication.comand;

import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ToDateCommand implements Command {
    private final Set<Training> trainings;
    private final Date toDate;

    public ToDateCommand(Set<Training> trainings, Date toDate) {
        this.trainings = trainings;
        this.toDate = toDate;
    }


    @Override
    public Set<Training> execute() {
        Set<Training> resultTrainings = new HashSet<>();
        for (Training training : trainings) {
            if (training.getTrainingDate().before(toDate)) {
                resultTrainings.add(training);
            }
        }
        return resultTrainings;
    }
}

