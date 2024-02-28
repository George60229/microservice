package com.example.jymapplication.storage;

import com.example.jymapplication.model.*;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Storage {

    public Map<String, MyEntity> data = new HashMap<>();
    private final Map<Class<? extends MyEntity>, String> classStringMap =
            Map.of(Trainee.class, "Trainee", Training.class, "Training", Trainer.class, "Trainer");

    public MyEntity addEntity(MyEntity myEntity) {
        String value = classStringMap.get(myEntity.getClass()) + myEntity.getId();
        data.put(value, myEntity);
        return myEntity;
    }

    public boolean removeEntity(int entityId, Class<? extends MyEntity> myEntityClass) {
        MyEntity myEntity = getEntity(entityId, myEntityClass);
        if (myEntity == null) {
            return false;
        }
        String key = myEntity.getClass().getSimpleName() + ":" + myEntity.getId();
        return data.remove(key, myEntity);
    }

    public MyEntity updateEntity(MyEntity myEntity) {
        String key = myEntity.getClass().getSimpleName() + ":" + myEntity.getId();
        data.remove(key);
        data.put(key, myEntity);
        return myEntity;
    }

    public MyEntity getEntity(int id, Class<? extends MyEntity> myEntityClass) {
        String key = myEntityClass.getSimpleName() + ":" + id;
        if (data.get(key) != null) {
            return data.get(key);
        }
        return null;
    }
}
