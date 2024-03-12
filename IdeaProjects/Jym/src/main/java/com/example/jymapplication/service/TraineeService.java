package com.example.jymapplication.service;

import com.example.jymapplication.dao.TraineeDao;
import com.example.jymapplication.model.Trainee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TraineeService {
    TraineeDao traineeDAO;

    @Autowired
    public TraineeService(TraineeDao trainerDAO) {
        this.traineeDAO = trainerDAO;
    }

    public Trainee createTrainee(Trainee trainee) {
        log.info("Create trainee:" + trainee.toString());
        return traineeDAO.add(trainee);
    }

    public Trainee editTrainee(Trainee trainee) {
        log.info("Update trainee:" + trainee.toString());
        return traineeDAO.update(trainee);
    }

    public boolean deleteTrainee(int traineeId) {
        log.info("Delete trainee with id:" + traineeId);
        return traineeDAO.delete(traineeId);
    }

    public Trainee selectTrainee(int traineeId) {
        log.info("Select trainee with id:" + traineeId);
        return traineeDAO.get(traineeId);
    }

}
