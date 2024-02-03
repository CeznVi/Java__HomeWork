package org.hw.hw3;

import org.hw.hw3.entity.Fraction;
import org.hw.hw3.interfaces.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.BinaryOperator;



public class homework3 implements Runnable{

    @Override
    public void run() {
        ///-----------------------TASK__1
        //task1();

        ////-----------------------TASK__2
        ///task2();

        ////-----------------------TASK__3
        //task3();

        ////-----------------------TASK__4
        task4();

    }

    ///-----------------------TASK__1
    public void task1(){
        ///Проверка является ли год високосным
        System.out.println("__________________________________________________");
        System.out.println("Проверка является ли год високосным ");
        LeapYearChecker leapYearChecker = year -> (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int yearToCheck = 2024;
        boolean isLeapYear = leapYearChecker.checkLeapYear(2024);
        System.out.println(yearToCheck + " год високосный = " + isLeapYear);

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 2, 3);

        /// Подсчет количества дней между двумя датами
        System.out.println("__________________________________________________");
        DaysBetweenCalculator daysBetweenCalculator = (date1, date2) -> (int) ChronoUnit.DAYS.between(date1,date2);
        int daysBetween = daysBetweenCalculator.calculateDaysBetween(startDate, endDate);
        System.out.println("Дней между " + startDate + " и " + endDate + ": " + daysBetween + " дней");

        /// Подсчёт количества полных недель между двумя датами;
        System.out.println("__________________________________________________");
        WeeksBetweenCalculator weeksBetweenCalculator = (date1, date2) -> date1.until(date2, ChronoUnit.WEEKS);
        long weeksBetween = weeksBetweenCalculator.calculateWeeksBetween(startDate, endDate);
        System.out.println("Подсчёт количества полных недель между двумя датами " + startDate +" и "+ endDate + " = " + weeksBetween + " недель");

        ////Подсчёт дня недели по полученной дате. Например, 20 июля 1969 года — воскресенье.
        System.out.println("__________________________________________________");
        DayOfWeekCalculator dayOfWeekCalculator = date -> date.getDayOfWeek().toString();

        LocalDate givenDate = LocalDate.of(1969, 7, 20);
        String dayOfWeek = dayOfWeekCalculator.calculateDayOfWeek(givenDate);

        System.out.println(givenDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + " was a " + dayOfWeek);
    }

    ///-----------------------TASK__2
    public void task2(){
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 3);
        Fraction result;

        ///Сумма двух дробей;
        ActionWithFractions addFractions = (f1, f2) -> {
            int resultNumerator = f1.numerator * f2.denominator + f2.numerator * f1.denominator;
            int resultDenominator = f1.denominator * f2.denominator;
            return new Fraction(resultNumerator, resultDenominator);
        };
        result = addFractions.with(fraction1, fraction2);
        System.out.println(fraction1 + " + " +fraction2 + " = " + result);

        ////Разница двух дробей;
        ActionWithFractions subFractions = (f1, f2) -> {
            int resultNumerator = f1.numerator * f2.denominator - f2.numerator * f1.denominator;
            int resultDenominator = f1.denominator * f2.denominator;
            return new Fraction(resultNumerator, resultDenominator);
        };
        result = subFractions.with(fraction1, fraction2);
        System.out.println(fraction1 + " - " +fraction2 + " = " + result);

        ////Произведение двух дробей;
        ActionWithFractions multiplyingFractions = (f1, f2) -> {
            int resultNumerator = f1.numerator * f2.numerator;
            int resultDenominator = f1.denominator * f2.denominator;
            return new Fraction(resultNumerator, resultDenominator);
        };
        result = multiplyingFractions.with(fraction1, fraction2);
        System.out.println(fraction1 + " * " +fraction2 + " = " + result);

        ////Деление двух дробей
        ActionWithFractions divideFractions = (f1, f2) -> {
            int resultNumerator = f1.numerator * f2.denominator;
            int resultDenominator = f1.denominator * f2.numerator;
            return new Fraction(resultNumerator, resultDenominator);
        };
        result = divideFractions.with(fraction1, fraction2);
        System.out.println(fraction1 + " / " +fraction2 + " = " + result);
    }

    ///-----------------------TASK__3
    public void task3(){
        BinaryOperator<Integer> maxOfFour = (a, b) -> Math.max(Math.max(a, b), Math.max(a, b));
        BinaryOperator<Integer> minOfFour = (a, b) -> Math.min(Math.min(a, b), Math.min(a, b));

        Integer maxInteger = maxOfFour.apply(10, Math.max(5, Math.max(8, 12)));
        System.out.println("Максимальное целое число: " + maxInteger);

        Integer minInteger = minOfFour.apply(10, Math.min(5, Math.min(8, 12)));
        System.out.println("Минимальное целое число: " + minInteger);

        BinaryOperator<Double> maxDouble = (a, b) -> Math.max(Math.max(a, b), Math.max(a, b));
        BinaryOperator<Double> minDouble = (a, b) -> Math.min(Math.min(a, b), Math.min(a, b));

        Double maxDoubleValue = maxDouble.apply(3.5, Math.max(8.9, Math.max(1.2, 5.7)));
        System.out.println("Максимальное double число: " + maxDoubleValue);

        Double minDoubleValue = minDouble.apply(3.5, Math.min(8.9, Math.min(1.2, 5.7)));
        System.out.println("Минимальное double число: " + minDoubleValue);

        BinaryOperator<String> maxString = (a, b) -> (a.compareTo(b) > 0) ? a : b;
        BinaryOperator<String> minString = (a, b) -> (a.compareTo(b) < 0) ? a : b;

        String maxStringValue = maxString.apply("яблоко", maxString.apply("груша", maxString.apply("цибуля", "можжевельник")));
        System.out.println("Макс строка: " + maxStringValue);

        String minStringValue = minString.apply("яблоко", minString.apply("груша", minString.apply("цибуля", "можжевельник")));
        System.out.println("Минимальная строка: " + minStringValue);
    }

    ///-----------------------TASK__4
    public void task4() {
        int[] array = {234, 2, -3, 32, -5, 423, 7, 8, 9, 811};

        int target = 811;
        int sum1 = conditionOf(array, n -> n != target);
        System.out.println("Проверка на равенство конкретному числу = "+ sum1);


        int a = -3;
        int b = 234;
        int sum2 = conditionOf(array, n -> n < a || n > b);
        System.out.println("Число не находится в диапазоне от A до B = "+ sum2);


        int sum3 = conditionOf(array, n -> n > 0);
        System.out.println("Проверка на положительность числа = " + sum3);

        int sum4 = conditionOf(array, n -> n < 0);
        System.out.println("Проверка на отрицательность числа = " + sum4);

    }

    public static int conditionOf(int[] array, ArrayCondition condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.condition(num)) {
                sum += num;
            }
        }
        return sum;
    }
}
