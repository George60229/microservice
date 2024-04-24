package com.example.jymapplication.comand;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.model.TrainingType;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandExecutor {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandExecutor(Set<Training> trainings, Object value) {
        commands.put("FromDate", new FromDateCommand(trainings, (LocalDate) value));
        commands.put("ToDate", new ToDateCommand(trainings, (LocalDate) value));
        commands.put("TrainerName", new TrainerNameCommand(trainings, (String) value));
        commands.put("TrainingType", new TrainingTypeCommand(trainings, (TrainingType) value));
        commands.put("TraineeType", new TraineeNameCommand(trainings, (String) value));
    }

    public Set<Training> executeCommand(String criteria) {
        if (commands.containsKey(criteria)) {
            Command command = commands.get(criteria);
            return command.execute();
        }
        return null;
    }
}
