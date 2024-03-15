package com.example.jymapplication.dao;

import com.example.jymapplication.dto.AuthorizeDto;
import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.repository.MyUserRepository;
import com.example.jymapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class GenericDao<T extends MyUser> {

    @Autowired
    MyUserRepository repository;

    @Autowired
    private UserService userService;

    public T getByUsername(String username) {
        return (T) repository.findByUsername(username);
    }

    public boolean checkPassword(AuthorizeDto authorizeDto) {
        MyUser myUser = getByUsername(authorizeDto.username);
        return myUser.getPassword().equals(authorizeDto.password);
    }

    public T changePassword(String username, String newPassword) {
        MyUser user = getByUsername(username);
        user.setPassword(newPassword);
        return update((T) user);
    }

    public T update(T trainee) {
        return repository.save(trainee);
    }

    public void changeActivity(String username) {
        MyUser user = getByUsername(username);
        user.setIsActive(!user.getIsActive());
        update((T) user);
    }

    public T add(T trainee) {
        trainee.setPassword(userService.generatePassword());
        trainee.setUsername(userService.generateUsername(trainee));
        return repository.save(trainee);
    }

    public T get(int id) {
        Optional<T> optionalTrainer = (Optional<T>) repository.findById(id);
        return optionalTrainer.orElse(null);

    }


}
