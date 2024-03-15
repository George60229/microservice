package com.example.jymapplication.repository;

import com.example.jymapplication.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
