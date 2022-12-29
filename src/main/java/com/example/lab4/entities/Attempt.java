package com.example.lab4.entities;

import com.example.lab4.util.DotProcessor;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class Attempt {
    @Id
    @GeneratedValue
    private long id;

    @Embedded
    private Dot dot;

    private boolean result;

    private ZonedDateTime processedAt;

    private long processingTime;

    private String owner;

    protected Attempt() {}

    public Attempt(Dot dot, long startOfProcessing, String owner) {
        this.dot = dot;
        this.result = DotProcessor.process(dot);
        this.processedAt = ZonedDateTime.now();
        this.processingTime = System.nanoTime() - startOfProcessing;
        this.owner = owner;
    }

    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }

    public Dot getDot() { return this.dot; }
    public void setDot(Dot dot) { this.dot = dot; }

    public boolean getResult() { return this.result; }
    public void setResult(boolean result) { this.result = result; }

    public ZonedDateTime getProcessedAt() { return this.processedAt; }
    public void setProcessedAt(ZonedDateTime processedAt) { this.processedAt = processedAt; }

    public long getProcessingTime() { return this.processingTime; }
    public void setProcessingTime(long processingTime) { this.processingTime = processingTime; }

    public String getOwner() { return this.owner; }
    public void setOwner(String owner) { this.owner = owner; }
}
