package com.example.jymapplication.repository;

import com.example.jymapplication.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    @Modifying
    @Query("update Trainer t set t.isActive = ?1")
    int updateIsActiveBy(Boolean isActive);

    Trainer findByUsername(String username);

}
