package com.example.jymapplication;

import com.example.jymapplication.dao.TraineeDao;
import com.example.jymapplication.dao.TrainerDao;
import com.example.jymapplication.dao.TrainingDao;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.service.TraineeService;
import com.example.jymapplication.service.TrainerService;
import com.example.jymapplication.service.TrainingService;
import com.example.jymapplication.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {

    public Storage initializeStorage() {
        Storage storage = new Storage();
        Trainee trainee = new Trainee();
        trainee.setFirstName("George");
        trainee.setLastName("Pashnev");
        trainee.setAddress("Odessa");
        trainee.setId(1);
        storage.addEntity(trainee);
        Trainer trainer = new Trainer();
        trainer.setFirstName("Michael");
        trainer.setLastName("Johnathan");
        trainer.setSpecialization("Sport");
        trainer.setId(2);
        Training training = new Training();
        training.setTrainingDuration(60);
        training.setTrainingName("Beginners");
        training.setId(3);
        storage.addEntity(trainee);
        storage.addEntity(trainer);
        storage.addEntity(training);
        return storage;
    }

    public TraineeService initializeTraineeService() {
        TraineeDao traineeDao = new TraineeDao(initializeStorage());
        return new TraineeService(traineeDao);
    }

    public TrainerService initializeTrainerService() {
        TrainerDao trainerDao = new TrainerDao(initializeStorage());
        return new TrainerService(trainerDao);
    }

    public TrainingService initializeTrainingService() {
        TrainingDao trainingDao = new TrainingDao(initializeStorage());
        return new TrainingService(trainingDao);
    }

    @Test
    public void TrainingServiceTest() {
        initializeStorage();
        TrainingService trainingService = initializeTrainingService();
        Training training = trainingService.selectTraining(3);
        Assertions.assertEquals(60, training.getTrainingDuration());
        Training training1 = new Training();
        training1.setId(5);
        training1.setTrainingName("Test");
        training1.setTrainingDuration(90);
        trainingService.createTraining(training1);
        Training myTraining = trainingService.selectTraining(5);
        Assertions.assertEquals(training1, myTraining);
    }

    @Test
    public void TrainerServiceTest() {
        initializeStorage();
        TrainerService trainerService = initializeTrainerService();
        Trainer trainer = trainerService.selectTrainer(2);
        Assertions.assertEquals("Sport", trainer.getSpecialization());
        Trainer myTrainer = new Trainer();
        myTrainer.setId(6);
        myTrainer.setSpecialization("Dance");
        myTrainer.setFirstName("Leh");
        trainerService.createTrainer(myTrainer);
        Trainer newTrainer = trainerService.selectTrainer(6);
        Assertions.assertEquals(newTrainer, myTrainer);
        myTrainer.setSpecialization("LALALALA");
        Trainer trainer1 = trainerService.updateTrainer(myTrainer);
        Assertions.assertEquals("LALALALA", trainer1.getSpecialization());
    }


    @Test
    public void TraineeServiceTest() {
        initializeStorage();
        TraineeService traineeService = initializeTraineeService();
        Trainee trainee = traineeService.selectTrainee(1);
        Assertions.assertEquals("Pashnev", trainee.getLastName());
        Trainee myTrainee = new Trainee();
        myTrainee.setId(6);
        myTrainee.setLastName("Ahmed");
        myTrainee.setFirstName("Leh");
        traineeService.createTrainee(myTrainee);
        Trainee trainee1 = traineeService.selectTrainee(6);
        Assertions.assertEquals(myTrainee, trainee1);
        myTrainee.setLastName("Ahmed1");
        Trainee trainee11 = traineeService.editTrainee(myTrainee);
        Assertions.assertEquals("Ahmed1", trainee11.getLastName());
        Assertions.assertTrue(traineeService.deleteTrainee(6));
        Assertions.assertFalse(traineeService.deleteTrainee(100));
    }
}
