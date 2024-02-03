package org.hw.hw3;

import org.hw.hw3.interfaces.DayOfWeekCalculator;
import org.hw.hw3.interfaces.DaysBetweenCalculator;
import org.hw.hw3.interfaces.LeapYearChecker;
import org.hw.hw3.interfaces.WeeksBetweenCalculator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class homework3 implements Runnable{

    @Override
    public void run() {
        ///-----------------------TASK__1
        task1();

    }

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

}
