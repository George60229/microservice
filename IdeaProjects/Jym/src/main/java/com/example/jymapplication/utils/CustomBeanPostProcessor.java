package com.example.jymapplication.utils;

import com.example.jymapplication.storage.Storage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

//todo
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private MyEnv myEnv;


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

            ArrayList<LinkedHashMap<String, Object>> data = objectMapper.readValue(new File(jsonFilePath), ArrayList.class);

            storage.setData(data);
            System.out.println(storage.getData());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}