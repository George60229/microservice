package com.example.jymapplication.dao;

import com.example.jymapplication.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDao {

    GenericDao genericDao;

    @Autowired
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public Trainer getTrainer(Integer id) {
        return (Trainer) genericDao.getEntity(id, Trainer.class);
    }

    public Trainer addTrainer(Trainer trainer) {
        return (Trainer) genericDao.addEntity(trainer);
    }

    public Trainer updateTrainer(Trainer trainer) {
        return (Trainer) genericDao.updateEntity(trainer);
    }
}
