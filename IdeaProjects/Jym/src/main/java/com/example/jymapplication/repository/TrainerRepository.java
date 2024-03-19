package com.example.jymapplication.repository;

import com.example.jymapplication.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Trainer findByUsername(String username);

}
