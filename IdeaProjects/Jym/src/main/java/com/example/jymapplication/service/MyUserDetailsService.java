package com.example.jymapplication.service;

import com.example.jymapplication.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final Map<String, Integer> loginAttempts = new HashMap<>();
    UserService userService;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userService.getByUsername(username);
        return new MyUserDetails(user.getPassword(), user.getUsername());
    }

    public void handleLoginAttempt(String username, String password) {
        if (loginAttempts.containsKey(username) && loginAttempts.get(username) >= 3) {
            userService.changeActivity(username, false);
            throw new BadCredentialsException("You are temporary blocked");
        }
        UserDetails user = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            loginAttempts.put(username, loginAttempts.getOrDefault(username, 0) + 1);
            throw new BadCredentialsException("Wrong credential");
        } else {
            loginAttempts.remove(username);
        }
    }
}
