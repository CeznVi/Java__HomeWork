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


    /**
     * Сложение дробей
     * @param otherFraction - другая дробь
     * @return Fraction - результат сложения
     */
    public Fraction add(Fraction otherFraction) {
        int commonDenominator = this.denominator * otherFraction.denominator;
        int sumNumerator = (this.numerator * otherFraction.denominator) + (otherFraction.numerator * this.denominator);
        Fraction result = new Fraction(sumNumerator, commonDenominator);
        result.simplify();
        return result;
    }

    /**
     * Вычитание дробей
     * @param otherFraction - другая дробь
     * @return Fraction - результат вычитания
     */
    public Fraction subtract(Fraction otherFraction) {
        int commonDenominator = this.denominator * otherFraction.denominator;
        int diffNumerator = (this.numerator * otherFraction.denominator) - (otherFraction.numerator * this.denominator);
        Fraction result = new Fraction(diffNumerator, commonDenominator);
        result.simplify();
        return result;
    }


}
