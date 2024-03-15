package com.example.jymapplication.service;

import com.example.jymapplication.dao.TrainingDao;
import com.example.jymapplication.model.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainingService {
    TrainingDao trainingDao;

    @Autowired
    public TrainingService(TrainingDao trainingDAO) {
        this.trainingDao = trainingDAO;
    }

    public Training createTraining(Training training) {
        log.info("Create training: " + training.toString());
        return trainingDao.add(training);
    }

    public Training selectTraining(int trainingId) {
        log.info("Select training with id: " + trainingId);
        return trainingDao.get(trainingId);
    }
}
