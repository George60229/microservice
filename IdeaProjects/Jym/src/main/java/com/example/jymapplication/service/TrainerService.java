package com.example.jymapplication.service;

import com.example.jymapplication.comand.CommandExecutor;
import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.enums.TrainerCriteria;
import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.repository.TrainerRepository;
import com.example.jymapplication.utils.Converter;
import com.example.jymapplication.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class TrainerService {
    TrainerRepository trainerRepository;

    @Autowired
    UserUtils userUtils;
    @Autowired
    Converter converter;

    @Autowired
    public TrainerService(TrainerRepository trainerDAO) {
        this.trainerRepository = trainerDAO;
    }

    public Trainer createTrainer(TrainerDto trainerDto) {
        log.info("Create trainer:" + trainerDto.toString());
        Trainer trainer = converter.trainerDtoToModel(trainerDto);
        trainer.setPassword(userUtils.generatePassword());
        trainer.setUsername(userUtils.generateUsername(trainer, (Set<Trainer>) trainerRepository.findAll()));
        return trainerRepository.save(trainer);

    }

    @Transactional
    public Trainer updateTrainer(TrainerDto trainerDto, AuthorizeDto authorizeDto, int userId) {
        if (checkCredential(authorizeDto)) {
            if (trainerRepository.findById(userId).isPresent()) {
                Trainer trainer = trainerRepository.findById(userId).get();
                trainer.setFirstName(trainerDto.getFirstName());
                trainer.setLastName(trainerDto.getLastName());
                trainer.setSpecialization(trainerDto.getSpecialization());
                trainerRepository.save(trainer);
            }

        }
        return null;
    }

    public Trainer selectTrainer(int trainerId, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            trainerRepository.findById(trainerId);
        }
        return null;
    }

    public Trainer changePassword(String username, String newPassword, AuthorizeDto authorizeDto) {

        if (checkCredential(authorizeDto)) {
            Trainer trainer = trainerRepository.findByUsername(username);
            trainer.setPassword(newPassword);
            return trainerRepository.save(trainer);

        }
        return null;
    }


    public void changeActivity(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            Trainer trainer = trainerRepository.findByUsername(username);
            trainer.setIsActive(!trainer.getIsActive());
            trainerRepository.save(trainer);
        }
    }

    public Set<Training> getTrainingByCriteria(String username, TrainerCriteria criteria, Object value, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            Trainer trainee = getByUsername(username);
            CommandExecutor commandExecutor = new CommandExecutor(trainee.getTrainings(), value);

            return commandExecutor.executeCommand(criteria.name());
        }
        return null;

    }


    public boolean checkCredential(AuthorizeDto authorizeDto) {
        MyUser myUser = getByUsername(authorizeDto.username);
        return myUser.getPassword().equals(authorizeDto.password);
    }

    private Trainer getByUsername(String username) {
        return trainerRepository.findByUsername(username);
    }

    public Set<Trainer> getAll() {
        return (Set<Trainer>) trainerRepository.findAll();
    }
}
