package com.example.jymapplication.repository;

import com.example.jymapplication.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    long deleteByTrainingNameAndTrainingDateAndTrainingDuration(String trainingName, LocalDate trainingDate, int trainingDuration);
}
