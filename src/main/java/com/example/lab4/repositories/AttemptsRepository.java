package com.example.lab4.repositories;

import com.example.lab4.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptsRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findAttemptByOwner(String owner);
}