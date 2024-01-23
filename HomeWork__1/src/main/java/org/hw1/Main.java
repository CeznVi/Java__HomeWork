package org.hw1;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //task1();

        task2();
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


}