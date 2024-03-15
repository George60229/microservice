package com.example.jymapplication.dao;

import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.enums.TraineeCriteria;
import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TraineeRepository;
import com.example.jymapplication.repository.TrainerRepository;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TraineeDao extends GenericDao<Trainee> {
    TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public TraineeDao(TraineeRepository traineeRepository,
                      TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public void delete(int id) {
        traineeRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        traineeRepository.deleteByUsername(username);
    }

    public Set<Trainer> getFreeTrainers(String username) {
        Trainee trainee = getByUsername(username);
        Set<Training> trainings = trainee.getTrainings();
        Set<Trainer> trainers = (Set<Trainer>) trainerRepository.findAll();
        for (Training trainer : trainings) {
            trainers.remove(trainer);
        }
        return trainers;
    }

    public Set<Training> getTrainingByCriteria(String username, TraineeCriteria criteria, Object value) {
        Trainee trainee = getByUsername(username);
        Set<Training> trainings = trainee.getTrainings();
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
            if (criteria.name().equals("TrainerName")) {
                if (training.getTrainer().getFirstName().equals(value)) {
                    resultTrainings.add(training);
                }
            }
            if (criteria.name().equals("TrainingType")) {
                if (training.getTrainingType().equals(value)) {
                    resultTrainings.add(training);
                }
            }
        }
        return resultTrainings;
    }


}
