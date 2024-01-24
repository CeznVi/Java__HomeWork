package org.hw1;
import exeptions.NumberOutOfRangeException;
import exeptions.SixDigitNumberException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //task1();

        //task2();

        //task3();

        //task4();

        //task5();

        task6();
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

    //// --------- TASK 5 SOLUTION ---------
    public static void task5(){
        System.out.println("Введите число от 1 до 12\n");

        try {
            int num = scanner.nextInt();

            if(num < 1 || num > 12) {
                throw new NumberOutOfRangeException("Введенное число должно быть в диапазоне от 1 до 12");
            }

            switch (num) {
                case 1:
                case 2:
                case 12:
                    System.out.println("Winter");
                    break;
                case 3:
                case 4:
                case 5:
                    System.out.println("Spring");
                    break;
                case 6:
                case 7:
                case 8:
                    System.out.println("Summer");
                    break;
                case 9:
                case 10:
                case 11:
                    System.out.println("Autumn");
                    break;
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Введенное значение не число");
        } catch (NumberOutOfRangeException numberOutOfRangeException) {
            System.out.println(numberOutOfRangeException.getMessage());
        }
    }

    //// --------- TASK 6 SOLUTION ---------
    public static void task6(){
        System.out.println("Введите количество метров которые необходимо перевести в другую еденицу измерения\n");

        try {
            double lenght = scanner.nextDouble();
            System.out.println("Чтобы перевести " + lenght + "метры\n" +
                    "\tНажмите 1 - метры в мили  \n" +
                    "\tНажмите 2 - метры в дюймы  \n" +
                    "\tНажмите 3 - метры в ярды  \n");

            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println(lenght / 1609);
                    break;
                }
                case 2: {
                    System.out.println(lenght * 39.37);
                    break;
                }
                case 3: {
                    System.out.println(lenght * 1.094);
                    break;
                }
                default:
                    System.out.println("Вы ввели не верное значение");
                    break;
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Введенное значение не число");
        }
    }


}