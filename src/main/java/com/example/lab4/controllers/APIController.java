package com.example.lab4.controllers;

import com.example.lab4.repositories.AttemptsRepository;
import com.example.lab4.entities.Attempt;
import com.example.lab4.entities.Dot;
import com.example.lab4.util.DotValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
    private final AttemptsRepository repository;

    public APIController(@Autowired AttemptsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAllAttempts")
    public List<Attempt> getAllAttempts(@Autowired Principal principal) {
        return this.repository.findAttemptByOwner(principal.getName());
    }

    @PostMapping("/addAttempt")
    public Attempt addAttempt(@RequestBody Dot dot, @Autowired Principal principal) throws IllegalArgumentException {
        if (!DotValidator.validate(dot)) throw new IllegalArgumentException();
        return this.repository.save(new Attempt(dot, System.nanoTime(), principal.getName()));
    }
}
