package com.example.jymapplication.service;

import com.example.jymapplication.model.MyEntity;
import com.example.jymapplication.model.User;
import com.example.jymapplication.storage.Storage;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;


@Service
public class UserService {

    public String calculateUsername(Storage storage, User user) {
        String username = user.getFirstName() + "." + user.getLastName();
        Map<String, MyEntity> userData = storage.data;
        int counter = 0;
        for (Map.Entry<String, MyEntity> entry : userData.entrySet()) {
            if (entry.getValue() instanceof User) {
                String result = ((User) entry.getValue()).getUsername().replaceAll("\\d", "");
                if (StringUtils.isNotEmpty(result)) {
                    counter++;
                }
            }
        }
        return counter == 0 ? username : username + counter;
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
