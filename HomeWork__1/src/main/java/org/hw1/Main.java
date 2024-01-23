package org.hw1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //task1();

        //task2();

        task3();
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
        Scanner scanner = new Scanner(System.in);

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
        Scanner scanner = new Scanner(System.in);

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


}