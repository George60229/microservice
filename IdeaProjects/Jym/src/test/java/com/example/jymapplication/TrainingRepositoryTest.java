package com.example.jymapplication;

import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class TrainingRepositoryTest {

    @Autowired
    private TrainingRepository trainingRepository;

    @Test
    public void testSaveAndGetUser() {
        Training training = new Training();
        training.setTrainingName("test");
        training.setId(1);
        trainingRepository.save(training);
        Training savedTraining = trainingRepository.findById(training.getId()).orElse(null);
        assert savedTraining != null;
        Assertions.assertEquals(savedTraining.getTrainingName(), training.getTrainingName());
    }
}
