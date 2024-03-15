package com.example.jymapplication.repository;

import com.example.jymapplication.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    void deleteByUsername(String username);

}
