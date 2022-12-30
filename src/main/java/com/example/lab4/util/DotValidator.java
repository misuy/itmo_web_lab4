package com.example.lab4.util;

import com.example.lab4.entities.Dot;

public class DotValidator {
    public static boolean validate(Dot dot) {
        return (dot.getX() >= -2) && (dot.getX() <= 2) && (dot.getY() >= -5) && (dot.getY() <= 3) && (dot.getR() >= 0.5) && (dot.getR() <= 2);
    }
}
