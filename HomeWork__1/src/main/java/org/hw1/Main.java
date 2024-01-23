package org.hw1;
import exeptions.SixDigitNumberException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //task1();

        //task2();

        //task3();

        task4();
    }


    //// --------- TASK 1 SOLUTION ---------
    public static void task1(){
        String str = "“Your time is limited,\n\tso don’t waste it\n\t\tliving someone else’s life”\n\t\t\tSteve Jobs";
        System.out.println(str);
    }

    //// --------- TASK 2 SOLUTION ---------
    public static void task2(){
        System.out.println("Введите два числа, \n" +
                "где - 1е число это значение\n" +
                "2е число это процент от первого числа\n");
        try
        {
            double number = scanner.nextDouble();
            double percent = scanner.nextDouble();
            System.out.println(number*percent/100);
        }
        catch (InputMismatchException ex)
        {
            System.out.println("Введеные данные не являются числами");
            task2();
        }


    }

    //// --------- TASK 3 SOLUTION ---------
    public static void task3(){
        System.out.println("Введите три числа и получите результат \n");
        try {
            int fNum = scanner.nextInt();
            int tNum = scanner.nextInt();
            int thrNum = scanner.nextInt();
            System.out.printf("%s%s%s", fNum, tNum, thrNum);
        }
        catch (InputMismatchException ex) {
            System.out.println("Введеные данные - не целое число\n");
        }


    }

    //// --------- TASK 4 SOLUTION ---------
    public static void task4(){
        System.out.println("Введите шестизначное число\n");
        try {
            int num = scanner.nextInt();

            if(num/100000 < 1 || num/100000 >10) { throw new SixDigitNumberException("Введенное число не шестизначное"); }

            int firstNum = num / 100000;
            int sixNum = num % 10;
            int twoNum = (num / 10000) % 10;
            int fiveNum = (num % 100) / 10;
            int centerNum = (num % 10000) / 100;

            System.out.printf("Введенное число = " + num +"\n");
            System.out.printf("Ответ = " + sixNum + fiveNum + centerNum + twoNum + firstNum);

        } catch (SixDigitNumberException sixDigitNumberException) {
            System.out.println(sixDigitNumberException.getMessage());
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Введенное значение не число");
        }


    }


}