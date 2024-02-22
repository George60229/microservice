package com.example.jymapplication.dao;

import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDao {

    @Autowired
    Storage storage;

    public Trainer getTrainer(Integer id) {
        return (Trainer) storage.getEntity(id);
    }

    public void addTrainer(Trainer trainer) {
        storage.addEntity(trainer);
    }

    public void updateTrainer(Trainer trainer,String key) {
        storage.updateEntity(trainer,key);
    }
}
