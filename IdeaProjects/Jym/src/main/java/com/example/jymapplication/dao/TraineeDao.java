package com.example.jymapplication.dao;

import com.example.jymapplication.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeDao {

    GenericDao genericDao;

    @Autowired
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public Trainee getTrainee(Integer id) {
        Object res = genericDao.getEntity(id, Trainee.class);
        if (res != null) {
            return (Trainee) res;
        }
        return null;
    }

    public Trainee addTrainee(Trainee trainee) {
        return (Trainee) genericDao.addEntity(trainee);
    }

    public Trainee updateTrainee(Trainee trainee) {
        return (Trainee) genericDao.updateEntity(trainee);
    }

    public boolean deleteTrainee(int userId) {
        return genericDao.removeEntity(userId, Trainee.class);
    }
}
