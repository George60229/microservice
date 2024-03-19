package com.example.jymapplication.service;

import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainee;
import com.example.jymapplication.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;


@Service
public class UserService {

    

    public String generateUsername(MyUser myEntity,Set<? extends MyUser> users) {
        int counter = 0;
        if (myEntity instanceof Trainer) {

            for (MyUser user : users) {
                if (user.getFirstName().equals((myEntity).getFirstName())
                        && user.getLastName().equals((myEntity).getLastName())) {
                    counter++;
                }
            }
        }
        if (counter == 0) {
            return myEntity.getFirstName() + "_" + (myEntity).getLastName();
        } else {
            return myEntity.getFirstName() + "_" + (myEntity).getLastName() + counter;
        }
    }

    public String generatePassword() {
        int minCharCode = 'a';
        int maxCharCode = 'z';
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(Character.toString(random.nextInt(maxCharCode - minCharCode + 1) + minCharCode));
        }
        return stringBuilder.toString();
    }
}
