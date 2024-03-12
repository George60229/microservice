package com.example.jymapplication;

import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StorageTests {
    private Storage initializeStorage() {
        Storage storage = new Storage();
        Trainee trainee = new Trainee();
        trainee.setFirstName("George");
        trainee.setLastName("Pashnev");
        trainee.setAddress("Odessa");
        trainee.setId(1);
        storage.addEntity(trainee);
        return storage;
    }

    @Test
    void getEntityTest() {
        Storage storage = initializeStorage();
        Trainee user = (Trainee) storage.getEntity(1, Trainee.class);
        Assertions.assertEquals("Odessa", user.getAddress());
        Assertions.assertEquals("George", user.getFirstName());
        Assertions.assertEquals("Pashnev", user.getLastName());

    }

    @Test
    void addEntityTest() {
        Storage storage = new Storage();
        Trainee user = new Trainee();
        user.setId(2);
        user.setAddress("Albania");
        user.setLastName("John");
        user.setFirstName("Alban");
        Trainee createdUser = (Trainee) storage.addEntity(user);
        Assertions.assertEquals(user.getId(), createdUser.getId());
        Assertions.assertEquals(user.getLastName(), createdUser.getLastName());
        Assertions.assertEquals(user.getAddress(), createdUser.getAddress());

    }

    @Test
    void createTrainingTest() {
        Storage storage = new Storage();
        Training training = new Training();
        training.setTrainingName("first");
        training.setId(1);
        training.setTrainingDuration(60);
        Training createdTraining = (Training) storage.addEntity(training);
        Assertions.assertEquals(training.getId(), createdTraining.getId());
        Assertions.assertEquals(training.getTrainingName(), createdTraining.getTrainingName());
        Assertions.assertEquals(training.getTrainingDuration(), createdTraining.getTrainingDuration());
    }

    @Test
    void removeEntityTest() {
        Storage storage = initializeStorage();
        Assertions.assertTrue(storage.removeEntity(1, Trainee.class));
    }

    @Test
    void updateEntityTest() {
        Storage storage = initializeStorage();
        Trainee trainee = (Trainee) storage.getEntity(1, Trainee.class);
        trainee.setFirstName("Ivan");
        storage.updateEntity(trainee);
        Assertions.assertEquals("Ivan", ((Trainee) storage.getEntity(1, Trainee.class)).getFirstName());
    }
}
