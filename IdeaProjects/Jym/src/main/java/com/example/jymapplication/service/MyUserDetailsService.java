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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final Map<String, Integer> loginAttempts = new HashMap<>();
    UserService userService;
    public PasswordEncoder passwordEncoder;
    private final Map<String, LocalDateTime> lockoutTimes = new HashMap<>();
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final Duration LOCKOUT_DURATION = Duration.ofMinutes(5);

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
        UserDetails user = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            handleFailedLoginAttempt(username);
            throw new BadCredentialsException("Wrong credentials");
        } else {
            resetLoginAttempts(username);
        }
    }

    private void handleFailedLoginAttempt(String username) {
        int attempts = loginAttempts.getOrDefault(username, 0);
        if (attempts == 0) {
            lockoutTimes.put(username, LocalDateTime.now());
        }
        attempts++;
        loginAttempts.put(username, attempts);
        LocalDateTime lockoutTime = lockoutTimes.get(username);
        if (attempts >= MAX_LOGIN_ATTEMPTS) {
            if (lockoutTime.plus(LOCKOUT_DURATION).isAfter(LocalDateTime.now())) {
                lockoutUser(username);
                throw new BadCredentialsException("Your account is temporarily locked. Please try again later.");
            } else {
                resetLoginAttempts(username);
                lockoutTimes.put(username, LocalDateTime.now());
                loginAttempts.put(username, 1);
            }
        }
    }

    private void lockoutUser(String username) {
        userService.changeActivity(username, false);
        resetLoginAttempts(username);
    }

    private void resetLoginAttempts(String username) {
        loginAttempts.remove(username);
    }
}
