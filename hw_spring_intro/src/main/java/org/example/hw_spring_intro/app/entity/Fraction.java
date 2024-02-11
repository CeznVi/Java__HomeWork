package org.example.hw_spring_intro.app.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * Класс описывающий сущность дроби
 */
public class Fraction {

    /**
     * Числитель
     */
    @Getter
    @Setter
    private int numerator;

    /**
     * Знаменатель
     */
    @Getter
    @Setter
    private int denominator;

    /** Конструктор дроби
     * @param numerator -- числитель
     * @param denominator -- знаменатель
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Проверить дробь на правильность
     * @return numerator < denominator
     */
    public boolean isProper() {
        return numerator < denominator;
    }

    /**
     * Сократить дробь
     */
    public void simplify() {
        int gcd = findGCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    /**
     * Найти общий делитель дроби рекурсивно.
     * @param a - числитель
     * @param b - знаменатель
     * @return общий делитель для числителя и знаменателя дроби
     */
    private int findGCD(int a, int b) {
        return b == 0 ? a : findGCD(b, a % b);
    }
}
