package com.example.jymapplication.repository;

import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.repository.TestConfig;
import com.example.jymapplication.repository.TrainerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class TrainerRepositoryTest {

    @Autowired
    private TrainerRepository trainerRepository;

    @Test
    public void testSaveAndGetUser() {
        Trainer trainer = new Trainer();
        trainer.setUsername("George");
        trainerRepository.save(trainer);
        Trainer savedUser = trainerRepository.findById(trainer.getId()).orElse(null);
        assert savedUser != null;
        Assertions.assertEquals(savedUser.getUsername(), trainer.getUsername());
    }

    @Test
    public void testDeleteUserByUsername() {
        Trainer trainer = new Trainer();
        trainer.setUsername("George");
        trainer.setId(1);
        trainerRepository.save(trainer);
        trainerRepository.deleteById(1);
        Trainer savedUser = trainerRepository.findById(trainer.getId()).orElse(null);
        Assertions.assertNull(savedUser);
    }

}
