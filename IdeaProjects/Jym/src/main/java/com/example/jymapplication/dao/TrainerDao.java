package com.example.jymapplication.dao;

import com.example.jymapplication.enums.TrainerCriteria;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainerRepository;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class TrainerDao {

    TrainerRepository trainerRepository;

    @Autowired
    private UserService userService;

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

    public Trainer getByUsername(String username) {
        return trainerRepository.findByUsername(username);
    }


    public Trainer changePassword(String username, String newPassword) {
        Trainer trainer = getByUsername(username);
        trainer.setPassword(newPassword);
        return update(trainer);
    }

    public Trainer update(Trainer trainee) {
        return trainerRepository.save(trainee);
    }

    public void changeActivity(String username) {
        Trainer user = getByUsername(username);
        user.setIsActive(!user.getIsActive());
        update(user);
    }

    public Trainer add(Trainer trainer) {
        trainer.setPassword(userService.generatePassword());
        trainer.setUsername(userService.generateUsername(trainer, findAll()));
        return trainerRepository.save(trainer);
    }

    public Trainer get(int id) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(id);
        return optionalTrainer.orElse(null);

    }

    public Set<Trainer> findAll() {
        return (Set<Trainer>) trainerRepository.findAll();
    }

}
