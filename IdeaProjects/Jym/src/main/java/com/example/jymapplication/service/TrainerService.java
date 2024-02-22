package com.example.jymapplication.service;

import com.example.jymapplication.dao.TrainerDao;
import com.example.jymapplication.model.Trainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainerService{

    TrainerDao trainerDao;

    @Autowired
    public TrainerService(TrainerDao trainerDAO) {


        this.trainerDao = trainerDAO;
    }


    public void createTrainer(Trainer trainer) {
        log.info("Create trainer:" + trainer.toString());
        trainerDao.addTrainer(trainer);
    }

    public void updateTrainer(Trainer trainer,String key) {
        log.info("Update trainer:" + trainer.toString());
        trainerDao.updateTrainer(trainer,key);
    }

    public Trainer selectTrainer(int trainerId) {
        log.info("Select trainer with id:" + trainerId);
        return trainerDao.getTrainer(trainerId);
    }
}
