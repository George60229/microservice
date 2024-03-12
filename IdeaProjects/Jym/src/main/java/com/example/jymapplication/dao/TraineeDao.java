package com.example.jymapplication.dao;

import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.storage.Storage;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeDao extends GenericDao<Trainee> {

    public TraineeDao(Storage storage) {
        super(storage);
        entityClass = Trainee.class;
    }

    public Trainee get(Integer id) {

        return getEntity(id);
    }

    public Trainee add(Trainee trainee) {
        return (Trainee) addEntity(trainee);
    }

    public Trainee update(Trainee trainee) {
        return (Trainee) updateEntity(trainee);
    }

    public boolean delete(int userId) {
        return removeEntity(userId);
    }
}
