package com.example.jymapplication.service;

import com.example.jymapplication.dao.TrainerDao;
import com.example.jymapplication.model.Trainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainerService {
    TrainerDao trainerDao;

    @Autowired
    public TrainerService(TrainerDao trainerDAO) {
        this.trainerDao = trainerDAO;
    }

    public Trainer createTrainer(Trainer trainer) {
        log.info("Create trainer:" + trainer.toString());
        return trainerDao.addEntity(trainer);
    }

    public Trainer updateTrainer(Trainer trainer) {
        log.info("Update trainer:" + trainer.toString());
        return trainerDao.updateEntity(trainer);

    }

    public Trainer selectTrainer(int trainerId) {
        log.info("Select trainer with id:" + trainerId);
        return trainerDao.getEntity(trainerId);
    }
}
