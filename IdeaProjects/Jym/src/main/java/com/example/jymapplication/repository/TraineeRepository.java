package com.example.jymapplication.repository;

import com.example.jymapplication.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

    Trainee findByUsername(String username);

    void deleteByUsername(String username);

}
