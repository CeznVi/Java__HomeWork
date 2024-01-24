package org.hw1;
import exeptions.NumberOutOfRangeException;
import exeptions.SixDigitNumberException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //task1();

        //task2();

        //task3();

        //task4();

        //task5();

        //task6();

        //task7();

        //task8();

        //task9();

        task10();
    }


    //// --------- TASK 1 SOLUTION ---------
    public static void task1(){
        String str = "“Your time is limited,\n\tso don’t waste it\n\t\tliving someone else’s life”\n\t\t\tSteve Jobs";
        System.out.println(str);
    }

    //// --------- TASK 2 SOLUTION ---------
    public static void task2(){
        System.out.println("Введите два числа,\n" +
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

    //// --------- TASK 7 SOLUTION ---------
    public static void task7(){
        try{
            System.out.println("Введите два два числа для определения диапазона поиска нечетных чисел");
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();

            if(num1 == num2) {
                System.out.println("Введенные числа равны, ничего не выйдет.");
                return;
            }

            if(num1 < num2) {
                for(int i = num1; i < num2; i++) {
                    if(i % 2 != 0){
                        System.out.print(i + ", ");
                    }
                }
            } else  {
                for(int i = num2; i < num1; i++) {
                    if(i % 2 != 0){
                        System.out.print(i + ", ");
                    }
                }
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Введенное значение не число");
        }
    }

    //// --------- TASK 8 SOLUTION ---------
    public static void task8(){
        try{
            System.out.println("Введите два два числа для определения диапазона начала и конца таблицы умножения");
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();

            if(num2 < num1) {
                int temp = num2;
                num2 = num1;
                num1 = temp;
            }

            for(int i = num1; i <= num2; i++) {
                for (int j = 1; j <= 10; j++) {
                    System.out.printf("%s * %s = %s, ", i, j, i*j);
                }
                System.out.println();
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Введенное значение не число");
        }
    }


    //// Генерация масива заданного размера случайными числами в указанном диапазоне
    public static int[] generateRandomArrayFixLength(int length, int min, int max) {
        Random random = new Random();
        int[] arr = new int[length];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt((max - min) + 1) + min;
        }
        return arr;
    }

    //// Генерация масива заданного размера случайными числами в указанном диапазоне
    public static void showArrayInConsole(int[] arr){
        for (int item : arr) {
            System.out.printf("%d  ", item);
        }
        System.out.println();
    }

    //// --------- TASK 9 SOLUTION ---------
    public static void task9(){
        int[] arr = generateRandomArrayFixLength(10, -10,10);

        int positiveInt = 0;
        int negativeInt = 0;
        int zeroInt = 0;

        System.out.println("Сгенерированный массив случайных чисел");
        showArrayInConsole(arr);

        //Сортировка масива для поиска мин и макс значения єлементов
        Arrays.sort(arr);

        for (int item : arr) {
            if(item < 0) {
                negativeInt++;
            } else if(item == 0) {
                zeroInt++;
            } else {
                positiveInt++;
            }
        }
        System.out.println();
        System.out.println("Минимальный элемент = " + arr[0]);
        System.out.println("Максимальный элемент = " + arr[arr.length-1]);
        System.out.println("Количество отрицательных элементов в массиве = " + negativeInt);
        System.out.println("Количество нулевых элементов в массиве = " + zeroInt);
        System.out.println("Количество положительных элементов в массиве = " + positiveInt);

    }

    //// --------- TASK 10 SOLUTION ---------
    public static void task10(){
        int[] arr = generateRandomArrayFixLength(30, -10,10);
        ArrayList<Integer> positiveIntArr = new ArrayList<Integer>();
        ArrayList<Integer> negativeIntArr = new ArrayList<Integer>();
        ArrayList<Integer> evenIntArr = new ArrayList<Integer>();
        ArrayList<Integer> oddIntArr = new ArrayList<Integer>();

        System.out.println("Сгенерированный массив случайных чисел");
        showArrayInConsole(arr);

        for (int item : arr) {
            if(item % 2 != 0) {
                oddIntArr.add(item);
            } else {
                evenIntArr.add(item);
            }

            if(item < 0) {
                negativeIntArr.add(item);
            } else {
                positiveIntArr.add(item);
            }
        }
        System.out.println("Массив из четных чисел");
        System.out.println(evenIntArr);
        System.out.println("Массив из нечетных чисел");
        System.out.println(oddIntArr);
        System.out.println("Массив из отрицательных чисел");
        System.out.println(negativeIntArr);
        System.out.println("Массив из положительных чисел");
        System.out.println(positiveIntArr);
    }

}