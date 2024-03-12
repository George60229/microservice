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
}
