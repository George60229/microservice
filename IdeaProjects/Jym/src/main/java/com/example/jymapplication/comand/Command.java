package com.example.jymapplication.comand;

import com.example.jymapplication.model.Training;

import java.util.Set;

public interface Command {
    Set<Training> execute();
}
