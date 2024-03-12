package com.example.jymapplication.dao;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.storage.Storage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDao extends GenericDao<Training> {

    public TrainingDao(Storage storage) {
        super(storage);
        entityClass = Training.class;
    }

    public Training get(Integer id) {
        entityClass = Training.class;
        return getEntity(id);
    }

    public Training addTraining(Training training) {
        return (Training) addEntity(training);
    }


}
