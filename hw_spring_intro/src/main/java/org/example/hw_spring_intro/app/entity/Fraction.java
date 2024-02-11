package org.example.hw_spring_intro.app.entity;


import lombok.Getter;
import lombok.Setter;

public class Fraction {
    @Getter
    @Setter
    private int numerator;

    @Getter
    @Setter
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public boolean isProper() {
        return numerator < denominator;
    }


}
