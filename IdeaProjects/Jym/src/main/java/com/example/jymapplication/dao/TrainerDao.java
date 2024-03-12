package com.example.jymapplication.dao;

import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.storage.Storage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDao extends GenericDao<Trainer> {

    public TrainerDao(Storage storage) {
        super(storage);
        entityClass = Trainer.class;
    }

    public Trainer get(Integer id) {
        return getEntity(id);
    }

    public Trainer add(Trainer trainer) {
        return (Trainer) addEntity(trainer);
    }

    public Trainer update(Trainer trainer) {
        return (Trainer) updateEntity(trainer);
    }
}
