package com.example.jymapplication.dao;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDao {

    GenericDao genericDao;

    @Autowired
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public Training getTraining(Integer id) {
        return (Training) genericDao.getEntity(id, Training.class);
    }

    public Training addTraining(Training training) {
        return (Training) genericDao.addEntity(training);
    }


}
