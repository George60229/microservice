package com.example.jymapplication.dao;

import com.example.jymapplication.model.MyEntity;
import com.example.jymapplication.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDao<T extends MyEntity> {
    Storage storage;
    Class<T> entityClass;

    @Autowired
    public GenericDao(Storage storage) {
        this.storage = storage;
    }

    public T getEntity(Integer id) {
        return (T) storage.getEntity(id, entityClass);
    }

    public MyEntity addEntity(MyEntity entity) {
        return storage.addEntity(entity);
    }

    public MyEntity updateEntity(MyEntity entity) {
        return storage.updateEntity(entity);
    }

    public boolean removeEntity(int entityId) {
        return storage.removeEntity(entityId, entityClass);
    }
}