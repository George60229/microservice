package com.example.jymapplication.storage;

import com.example.jymapplication.model.*;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component

public class Storage {


    @Autowired
    private UserService userService;
    //add comment
    private Map<String, Entity> data = new HashMap<>();


    public void addEntity(Entity entity) {

        String value = "";
        if (entity instanceof Trainer) {
            value = "trainer:" + ((Trainer) entity).getUserId();

        }
        if (entity instanceof Trainee) {
            value = "trainee:" + ((Trainee) entity).getUserId();
        }
        if (entity instanceof Training) {
            value = "training:" + ((Training) entity).getTrainingId();
        }
        data.put(value, entity);

    }

    public void removeEntity(String entityId) {
        data.remove(entityId);
    }

    public Map<String, Entity> getData() {
        return data;
    }

    public void updateEntity(Entity entity, String key) {
        data.remove(key);
        data.put(key, entity);
    }

    public Entity getEntity(int id) {
        return data.get(String.valueOf(id));
    }

    public void setData(ArrayList<LinkedHashMap<String, Object>> arrayList) {
        for (LinkedHashMap<String, Object> map : arrayList) {
            Entity entity = createEntityFromMap(map);
            addEntity(entity);
        }
    }

    private Entity createEntityFromMap(LinkedHashMap<String, Object> linkedHashMap) {
        String className = (String) linkedHashMap.get("className");
        if ("training".equals(className)) {
            Training training = new Training();
            training.setTrainingId((Integer) linkedHashMap.get("trainingId"));
            training.setTrainingDate((String) linkedHashMap.get("trainingDate"));
            training.setTrainingDuration((Integer) linkedHashMap.get("trainingDuration"));
            training.setTrainingType((TrainingType) linkedHashMap.get("trainingType"));
            training.setTrainingName((String) linkedHashMap.get("trainingName"));
            training.setTrainerId((Integer) linkedHashMap.get("trainingId"));
            training.setTraineeId((Integer) linkedHashMap.get("traineeId"));
            return training;
        }
        User entity = null;


        if ("trainer".equals(className) || "trainee".equals(className)) {
            if ("trainer".equals(className)) {
                entity = new Trainer();
            } else {
                entity = new Trainee();
            }

            entity.setLastName((String) linkedHashMap.get("lastName"));
            entity.setFirstName((String) linkedHashMap.get("firstName"));
            entity.setUserId((Integer) linkedHashMap.get("userId"));


            if ("trainer".equals(className)) {
                ((Trainer) entity).setSpecialization((String) linkedHashMap.get("specialization"));
            } else {
                ((Trainee) entity).setDateOfBirth((String) linkedHashMap.get("dateOfBirth"));//todo
                ((Trainee) entity).setAddress((String) linkedHashMap.get("address"));
            }


            entity.setUsername(userService.calculateUsername(this, entity));
            entity.setPassword(userService.generatePassword());
        }

        return entity;
    }


}
