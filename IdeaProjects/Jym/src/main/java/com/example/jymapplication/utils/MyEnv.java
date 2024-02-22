package com.example.jymapplication.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyEnv implements EnvironmentAware {

    private Environment environment;


    @Autowired
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getValue(String valuePath) {
        return environment.getProperty(valuePath);

    }
}
