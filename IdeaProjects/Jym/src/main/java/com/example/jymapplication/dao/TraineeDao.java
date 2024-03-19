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
import java.util.Optional;
import java.util.Set;

@Repository
public class TraineeDao {
    TraineeRepository traineeRepository;


    @Autowired
    private UserService userService;

    public TraineeDao(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;

    }

    public void delete(int id) {
        traineeRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        traineeRepository.deleteByUsername(username);
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

    public Trainee getByUsername(String username) {
        return traineeRepository.findByUsername(username);
    }

    public boolean checkPassword(AuthorizeDto authorizeDto) {
        MyUser myUser = getByUsername(authorizeDto.username);
        return myUser.getPassword().equals(authorizeDto.password);
    }

    public Trainee changePassword(String username, String newPassword) {
        Trainee user = getByUsername(username);
        user.setPassword(newPassword);
        return update(user);
    }

    public Trainee update(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    public void changeActivity(String username) {
        Trainee user = getByUsername(username);
        user.setIsActive(!user.getIsActive());
        update(user);
    }

    public Trainee add(Trainee trainee) {
        trainee.setPassword(userService.generatePassword());
        trainee.setUsername(userService.generateUsername(trainee,getAll()));
        return traineeRepository.save(trainee);
    }

    public Trainee get(int id) {
        Optional<Trainee> optionalTrainee = traineeRepository.findById(id);
        return optionalTrainee.orElse(null);

    }

    public Set<Trainee> getAll(){
        return (Set<Trainee>) traineeRepository.findAll();
    }



}
