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
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.time.format.DateTimeFormatter;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private UserService userService;
    @Autowired
    private MyEnv myEnv;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Map<String, Function<LinkedHashMap<String, Object>, MyEntity>> mapOfFunctions = new HashMap<>();

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
        mapOfFunctions.put("training", x -> new Training((String) x.get("trainingName"),
                (TrainingType) x.get("trainingType"), LocalDate.parse((String) x.get("trainingDate"), formatter),
                (Integer) x.get("trainingDuration")));
        mapOfFunctions.put("trainer", x -> new Trainer((String) x.get("firstName"),
                (String) x.get("secondName"), userService.calculateUsername(storage, (String) x.get("firstName"), (String) x.get("lastName")),
                userService.generatePassword(), Boolean.parseBoolean((String) x.get("isActive")),
                (String) x.get("specialization")));
        mapOfFunctions.put("trainee", x -> new Trainee((String) x.get("firstName"),
                (String) x.get("secondName"), userService.calculateUsername(storage, (String) x.get("firstName"), (String) x.get("lastName")),
                userService.generatePassword(), Boolean.parseBoolean((String) x.get("isActive")),
                LocalDate.parse((String) x.get("dateOfBirth"), formatter), (String) x.get("address")));
        for (LinkedHashMap<String, Object> map : arrayList) {
            MyEntity myEntity = createEntityFromMap(map);
            storage.addEntity(myEntity);
        }
    }

    private MyEntity createEntityFromMap(LinkedHashMap<String, Object> values) {
        String className = (String) values.get("entityType");
        return mapOfFunctions.get(className).apply(values);
    }
}