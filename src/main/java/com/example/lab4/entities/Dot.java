package com.example.lab4.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Embeddable
public class Dot {
    private double x;
    private double y;
    private double r;

    public Dot() {}

    public Dot(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX() { return this.x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return this.y; }
    public void setY(double y) { this.y = y; }

    public double getR() { return this.r; }
    public void setR(double r) { this.r = r; }
}
