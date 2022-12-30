package com.example.lab4.util;

import com.example.lab4.entities.Dot;
import org.springframework.context.annotation.Bean;

public class DotProcessor {
    public static boolean process(Dot dot) {
        if (dot.getX() >= 0) {
            if (dot.getY() >= 0) {
                return (dot.getX() <= dot.getR()) && (dot.getY() <= dot.getR());
            }
            else {
                return dot.getY() >= (2 * dot.getX() - dot.getR());
            }
        }
        else {
            if (dot.getY() <= 0) {
                return Math.sqrt(Math.pow(dot.getX(), 2) + Math.pow(dot.getY(), 2)) <= dot.getR();
            }
        }
        return false;
    }
}
