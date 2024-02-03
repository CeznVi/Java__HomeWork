package org.hw.hw3.entity;

public class Fraction {
    public int numerator;
    public int denominator;

    public Fraction(int num, int denom){
        numerator = num;
        denominator = denom;
    }

    @Override
    public String toString() {
        return numerator + "/" +denominator;
    }
}
