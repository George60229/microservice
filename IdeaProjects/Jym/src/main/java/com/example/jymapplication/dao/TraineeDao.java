package com.example.jymapplication.dao;

import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeDao {
    @Autowired
    Storage storage;

    public Trainee getTrainee(Integer id) {
        return (Trainee) storage.getEntity(id);
    }

    public void addTrainee(Trainee trainee) {
        storage.addEntity(trainee);
    }

    public void updateTrainee(Trainee trainer,String key) {
        storage.updateEntity(trainer,key);
    }

    public void deleteTrainee(String namespace) {
        storage.removeEntity(namespace);
    }
}
