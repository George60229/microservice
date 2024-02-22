package com.example.jymapplication.service;

import com.example.jymapplication.model.Entity;
import com.example.jymapplication.model.User;
import com.example.jymapplication.storage.Storage;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

//todo
@Service
public class UserService {


    public String calculateUsername(Storage storage, User user) {
        String username = user.getFirstName() + "." + user.getLastName();
        Map<String, Entity> userData = storage.getData();

        int counter = 0;

        for (Map.Entry<String, Entity> entry : userData.entrySet()) {
            if (entry.getValue() instanceof User) {

                String result = ((User) entry.getValue()).getUsername().replaceAll("\\d", "");
                if (!result.equals("")) {

                    counter++;
                }
            }

        }
        if (counter == 0) {
            return username;
        } else {
            return username + counter;
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
