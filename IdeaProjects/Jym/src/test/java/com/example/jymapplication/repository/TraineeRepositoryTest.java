package com.example.jymapplication.repository;


import com.example.jymapplication.model.Trainee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class TraineeRepositoryTest {

    @Autowired
    private TraineeRepository traineeRepository;

    @Test
    public void testSaveAndGetUser() {
        Trainee trainee = new Trainee();
        trainee.setUsername("George");
        traineeRepository.save(trainee);
        Trainee savedUser = traineeRepository.findById(trainee.getId()).orElse(null);
        assert savedUser != null;
        Assertions.assertEquals(savedUser.getUsername(), trainee.getUsername());
    }

    @Test
    public void testDeleteUserByUsername() {
        Trainee trainee = new Trainee();
        trainee.setUsername("George");
        trainee.setId(1);
        traineeRepository.save(trainee);
        traineeRepository.deleteByUsername("George");
        Trainee savedUser = traineeRepository.findById(trainee.getId()).orElse(null);
        Assertions.assertNull(savedUser);
    }

    @Test
    public void testDeleteUserById() {
        Trainee trainee = new Trainee();
        trainee.setUsername("George");
        trainee.setId(1);
        traineeRepository.save(trainee);
        traineeRepository.deleteById(1);
        Trainee savedUser = traineeRepository.findById(trainee.getId()).orElse(null);
        Assertions.assertNull(savedUser);
    }
}
