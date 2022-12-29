package com.example.lab4.repositories;

import com.example.lab4.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptsRepository extends JpaRepository<Attempt, Long> {
}