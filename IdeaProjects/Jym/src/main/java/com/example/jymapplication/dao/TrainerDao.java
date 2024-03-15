package com.example.jymapplication.dao;

import com.example.jymapplication.enums.TrainerCriteria;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainerRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TrainerDao extends GenericDao<Trainer> {

    TrainerRepository trainerRepository;

    public TrainerDao(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Set<Training> getTrainingByCriteria(String username, TrainerCriteria criteria, Object value) {
        Trainer trainer = getByUsername(username);
        Set<Training> trainings = trainer.getTraining();
        Set<Training> resultTrainings = new HashSet<>();

        for (Training training : trainings) {
            if (criteria.name().equals("FromDate")) {
                if (training.getTrainingDate().after((Date) value)) {
                    resultTrainings.add(training);
                }
            }
            if (criteria.name().equals("ToDate")) {
                if (training.getTrainingDate().before((Date) value)) {
                    resultTrainings.add(training);
                }
            }
            if (criteria.name().equals("TraineeName")) {
                for (Trainee trainee : training.getTrainees()) {
                    if (trainee.getFirstName().equals(value)) {
                        resultTrainings.add(training);
                    }
                }
            }
        }
        return resultTrainings;
    }

}
