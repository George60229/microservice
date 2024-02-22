package com.example.jymapplication.dao;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDao {
    @Autowired
    Storage storage;

    public Training getTraining(Integer id) {
        return (Training) storage.getEntity(id);
    }

    public void addTraining(Training training) {
        storage.addEntity(training);
    }



}
