package com.example.jymapplication.repository;

import com.example.jymapplication.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {


    @Modifying
    @Query("update Trainee t set t.isActive = ?1")
    int updateIsActiveBy(Boolean isActive);
    Trainee findByUsername(String username);
    void deleteByUsername(String username);

}
