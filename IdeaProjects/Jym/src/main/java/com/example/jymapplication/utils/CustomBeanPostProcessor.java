package com.example.jymapplication.utils;

import com.example.jymapplication.model.*;
import com.example.jymapplication.service.UserService;
import com.example.jymapplication.storage.Storage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private MyEnv myEnv;

    @Autowired
    private UserService userService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Storage storage) {
            initializeStorage(storage);
        }
        return bean;
    }

    public void initializeStorage(Storage storage) {
        String jsonFilePath = myEnv.getValue("storage.data.file.path");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayList<LinkedHashMap<String, Object>> data =
                    objectMapper.readValue(new File(jsonFilePath), ArrayList.class);
            setData(storage, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(Storage storage, ArrayList<LinkedHashMap<String, Object>> arrayList) {
        for (LinkedHashMap<String, Object> map : arrayList) {
            MyEntity myEntity = createEntityFromMap(map, storage);
            storage.addEntity(myEntity);
        }
    }

    private MyEntity createEntityFromMap(LinkedHashMap<String, Object> linkedHashMap, Storage storage) {
        String className = (String) linkedHashMap.get("className");
        if ("training".equals(className)) {
            Training training = new Training();
            training.setTrainingId((Integer) linkedHashMap.get("trainingId"));
            training.setTrainingDate((String) linkedHashMap.get("trainingDate"));
            training.setTrainingDuration((Integer) linkedHashMap.get("trainingDuration"));
            training.setTrainingType((TrainingType) linkedHashMap.get("trainingType"));
            training.setTrainingName((String) linkedHashMap.get("trainingName"));
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
                ((Trainee) entity).setDateOfBirth((String) linkedHashMap.get("dateOfBirth"));
                ((Trainee) entity).setAddress((String) linkedHashMap.get("address"));
            }
            entity.setUsername(userService.calculateUsername(storage, entity));
            entity.setPassword(userService.generatePassword());
        }
        return entity;
    }
}