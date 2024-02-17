package org.example.hw_spring_intro.app.controllers;

import org.example.hw_spring_intro.app.entity.Fraction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FractionController {
    /**
     * Выполнить проверку дроби на правильность
     * @param numerator - числитель дроби
     * @param denominator - знаменатель дроби
     * @return Правду - если числитель меньше знаменателя, иначе - ложь.
     */
    @GetMapping("/isProper")
    public String checkFraction(@RequestParam int numerator,
                                @RequestParam int denominator){
        Fraction fraction = new Fraction(numerator, denominator);
        if (fraction.isProper()) {
            return "Дробь правильная";
        } else {
            return "Дробь НЕ правильная";
        }
    }

    /**
     * Сократить дробь
     * @param numerator - числитель дроби
     * @param denominator - знаменатель дроби
     * @return возвращает сокращенную дробь
     */
    @GetMapping("/simplify")
    public Fraction simplifyFraction(@RequestParam int numerator, @RequestParam int denominator) {
        Fraction fraction = new Fraction(numerator, denominator);
        fraction.simplify();
        return fraction;
    }


    /**
     * Сложение двух дробей
     * @param num1 числитель 1й дроби
     * @param denom1 знаменатель 1й дроби
     * @param num2  числитель 2й дроби
     * @param denom2 знаменатель 2й дроби
     * @return Freaction - результат сложения двух дробей
     */
    @GetMapping("/add")
    public Fraction addFractions(@RequestParam int num1, @RequestParam int denom1,
                                 @RequestParam int num2, @RequestParam int denom2){
        Fraction fr1 = new Fraction(num1, denom1);
        Fraction fr2 = new Fraction(num2, denom2);

        return fr1.add(fr2);
    }


    /**
     * Вычитание двух дробей
     * @param num1 числитель 1й дроби
     * @param denom1 знаменатель 1й дроби
     * @param num2  числитель 2й дроби
     * @param denom2 знаменатель 2й дроби
     * @return Freaction - результат вычитания двух дробей
     */
    @GetMapping("/subtract")
    public Fraction subtractFractions(@RequestParam int num1, @RequestParam int denom1,
                                      @RequestParam int num2, @RequestParam int denom2){
        Fraction fr1 = new Fraction(num1, denom1);
        Fraction fr2 = new Fraction(num2, denom2);
        return fr1.subtract(fr2);
    }

    /**
     * Умножения двух дробей
     * @param num1 числитель 1й дроби
     * @param denom1 знаменатель 1й дроби
     * @param num2  числитель 2й дроби
     * @param denom2 знаменатель 2й дроби
     * @return Freaction - результат умножения двух дробей
     */
    @GetMapping("/multiply")
    public Fraction multiplyFractions(@RequestParam int num1, @RequestParam int denom1,
                                      @RequestParam int num2, @RequestParam int denom2){
        Fraction fr1 = new Fraction(num1, denom1);
        Fraction fr2 = new Fraction(num2, denom2);
        return fr1.multiply(fr2);
    }



}
