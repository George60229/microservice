package com.example.jymapplication.utils;

import com.example.jymapplication.model.MyUser;
import com.example.jymapplication.model.Trainer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;


@Service
public class UserUtils {


    public String generateUsername(MyUser myEntity, List<? extends MyUser> users) {
        int counter = 0;


        for (MyUser user : users) {
            if (user.getFirstName().equals((myEntity).getFirstName())
                    && user.getLastName().equals((myEntity).getLastName())) {
                counter++;
            }
        }

        return myEntity.getFirstName() + "_" + (myEntity).getLastName() + (counter == 0 ? "" : counter);
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
