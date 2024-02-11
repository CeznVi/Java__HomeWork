package org.example.hw_spring_intro.app.controllers;

import org.example.hw_spring_intro.app.entity.Fraction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;

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


}
