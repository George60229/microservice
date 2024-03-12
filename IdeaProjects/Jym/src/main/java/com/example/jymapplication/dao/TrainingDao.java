package com.example.jymapplication.dao;

import com.example.jymapplication.model.MyEntity;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.storage.Storage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDao extends GenericDao<Training> {

    public TrainingDao(Storage storage) {
        super(storage);
        entityClass = Training.class;
    }

    @Override
    public boolean removeEntity(int id) {
        throw new UnsupportedOperationException("You can't delete training");
    }

    @Override
    public Training updateEntity(MyEntity myEntity) {
        throw new UnsupportedOperationException("You can't update training");
    }
}
