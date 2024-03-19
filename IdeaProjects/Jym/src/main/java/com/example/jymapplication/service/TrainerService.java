package com.example.jymapplication.service;

import com.example.jymapplication.dao.TrainerDao;
import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.dto.TrainerDto;
import com.example.jymapplication.enums.TrainerCriteria;
import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainer;
import com.example.jymapplication.model.Training;
import com.example.jymapplication.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class TrainerService {
    TrainerDao trainerDao;
    @Autowired
    Converter converter;

    @Autowired
    public TrainerService(TrainerDao trainerDAO) {
        this.trainerDao = trainerDAO;
    }

    public Trainer createTrainer(TrainerDto trainerDto) {
        log.info("Create trainer:" + trainerDto.toString());
        return trainerDao.add(converter.trainerDtoToModel(trainerDto));
    }

    public Trainer updateTrainer(TrainerDto trainerDto, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? trainerDao.update(converter.trainerDtoToModel(trainerDto)) : null;
    }

    public Trainer selectTrainer(int trainerId, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? trainerDao.get(trainerId) : null;
    }

    public Trainer changePassword(String username, String newPassword, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? trainerDao.changePassword(username, newPassword) : null;
    }


    public void changeActivity(String username, AuthorizeDto authorizeDto) {
        if (checkCredential(authorizeDto)) {
            log.info("Change activity status:" + username);
            trainerDao.changeActivity(username);
        }
    }

    public Set<Training> getTrainingByCriteria(String username, TrainerCriteria criteria, Object value, AuthorizeDto authorizeDto) {
        return checkCredential(authorizeDto) ? trainerDao.getTrainingByCriteria(username, criteria, value) : null;
    }


    public boolean checkCredential(AuthorizeDto authorizeDto) {
        MyUser myUser = getByUsername(authorizeDto.username);
        return myUser.getPassword().equals(authorizeDto.password);
    }

    private Trainer getByUsername(String username) {
        return trainerDao.getByUsername(username);
    }

    public Set<Trainer> getAll() {
        return trainerDao.findAll();
    }
}
