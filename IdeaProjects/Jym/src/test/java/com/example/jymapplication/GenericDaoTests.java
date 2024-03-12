package com.example.jymapplication;

import com.example.jymapplication.dao.TraineeDao;
import com.example.jymapplication.dao.TrainerDao;
import com.example.jymapplication.dao.TrainingDao;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenericDaoTests {

    TraineeDao traineeDao = new TraineeDao(initializeStorage());
    TrainerDao trainerDao = new TrainerDao(initializeStorage());
    TrainingDao trainingDao = new TrainingDao(initializeStorage());

    private Storage initializeStorage() {
        Storage storage = new Storage();
        Trainee trainee = new Trainee();
        trainee.setFirstName("George");
        trainee.setLastName("Pashnev");
        trainee.setAddress("Odessa");
        trainee.setId(1);
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

    @Test
    public void getTest() {
        Trainee user = traineeDao.getEntity(1);
        Assertions.assertEquals("Odessa", user.getAddress());
        Assertions.assertEquals("George", user.getFirstName());
        Assertions.assertEquals("Pashnev", user.getLastName());
        Trainee trainee = traineeDao.getEntity(1);
        Trainer trainer = trainerDao.getEntity(2);
        Training training = trainingDao.getEntity(3);
        Assertions.assertEquals("Odessa", trainee.getAddress());
        Assertions.assertEquals("George", trainee.getFirstName());
        Assertions.assertEquals("Pashnev", trainee.getLastName());
        Assertions.assertEquals("Sport", trainer.getSpecialization());
        Assertions.assertEquals("Michael", trainer.getFirstName());
        Assertions.assertEquals("Johnathan", trainer.getLastName());
        Assertions.assertEquals(60, training.getTrainingDuration());
        Assertions.assertEquals("Beginners", training.getTrainingName());
    }

    @Test
    public void addTest() {
        Trainer user = new Trainer();
        user.setId(2);
        user.setFirstName("Albert");
        user.setLastName("Almer");
        Trainer createdTrainer = (Trainer) trainerDao.addEntity(user);
        Assertions.assertEquals(user.getId(), createdTrainer.getId());
        Assertions.assertEquals(user.getLastName(), createdTrainer.getLastName());
        Assertions.assertEquals(user.getFirstName(), createdTrainer.getFirstName());
        Trainer trainer = new Trainer();
        trainer.setFirstName("Michael1");
        trainer.setLastName("Johnathan1");
        trainer.setSpecialization("Sport1");
        trainer.setId(5);
        Training training = new Training();
        training.setTrainingDuration(60);
        training.setTrainingName("Beginners");
        training.setId(6);
        Training training1 = trainingDao.addEntity(training);
        Trainer trainer1 = trainerDao.addEntity(trainer);
        Assertions.assertEquals(training1.getTrainingDuration(), training.getTrainingDuration());
        Assertions.assertEquals(trainer1.getSpecialization(), trainer.getSpecialization());
    }

    @Test
    public void deleteTest() {
        Assertions.assertTrue(traineeDao.removeEntity(1));
    }

    @Test
    public void updateTest() {
        Trainee trainee = traineeDao.getEntity(1);
        trainee.setFirstName("Ivan");
        traineeDao.updateEntity(trainee);
        Assertions.assertEquals("Ivan", (traineeDao.getEntity(1)).getFirstName());
    }
}
