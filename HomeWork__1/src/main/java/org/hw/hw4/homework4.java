package org.hw.hw4;

import org.hw.hw4.jobs.*;
import org.hw.hw4.utility.DirectoryCopy;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class homework4 implements Runnable{

    @Override
    public void run() {

        ///-----------------------TASK__1
        //task1();

        ////-----------------------TASK__2
        //task2();

        ////-----------------------TASK__3
        //task3();

        ////-----------------------TASK__4
        task4();
    }

    ///-----------------------TASK__1--------------------------------
    private void task1() {
        int arrLenght = 10;
        int[] arr = new int[arrLenght];

        GenerateRndIntArrJob genArrayJob = new GenerateRndIntArrJob(arr);
        GetSumArrElemJob getSumJob = new GetSumArrElemJob(arr);
        GetAvarageInArrJob getAvarageInArrJob = new GetAvarageInArrJob(arr);
        Thread generateArrThread = new Thread(genArrayJob);
        Thread getSumThread = new Thread(getSumJob);
        Thread getAvarageThread = new Thread(getAvarageInArrJob);

        generateArrThread.start();

        try {
            //Ждем завершения потока заполнения массива
            generateArrThread.join();
            //После завершения генерации стартует два треда
            getSumThread.start();
            getAvarageThread.start();

            //Основной поток ожидает завершения
            getSumThread.join();
            getAvarageThread.join();

        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("Массив:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\nСумма элементов массива: " + getSumJob.GetSum());
        System.out.println("Среднеарифметическое значение: " + getAvarageInArrJob.GetAvarage());

    }

    ///-----------------------TASK__2--------------------------------
    private void task2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: (Enter- путь по умолчанию C:\\test\\  )  ");
        String inputFilePath = scanner.nextLine();
        if(inputFilePath.length() < 3) inputFilePath = "C:\\test\\readFile.txt";

        ExecutorService executor = Executors.newFixedThreadPool(3);

        String primeOutputFilePath = "C:\\test\\prime_output.txt";
        String factorialOutputFilePath = "C:\\test\\factorial_output.txt";

        AtomicInteger primeCount = new AtomicInteger(0);
        AtomicInteger factorialCount = new AtomicInteger(0);

        Runnable primeFinder = new PrimeFinderJob(inputFilePath, primeOutputFilePath, primeCount);
        Runnable factorialCalculator = new FactorialCalculatorJob(inputFilePath, factorialOutputFilePath,factorialCount);

        executor.execute(() -> {
            try {
                primeFinder.run();
            } finally {
                synchronized (factorialCalculator) {
                    factorialCalculator.notify();
                }
            }
        });

        executor.execute(() -> {
            synchronized (factorialCalculator) {
                try {
                    factorialCalculator.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            factorialCalculator.run();
        });

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Простых чисел найдено: " + primeCount.get());
        System.out.println("Результат записан в файл по адресу " + primeOutputFilePath);
        System.out.println("Факториалов просчитано: " + factorialCount.get());
        System.out.println("Результат записан в файл по адресу " + factorialOutputFilePath);
    }

    ///-----------------------TASK__3--------------------------------
    private void task3(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к папке: (Enter- путь по умолчанию C:\\test\\Original  )  ");
        String original = "C:\\test\\Original";
        String copyTo = "C:\\test\\22";

        String inputFilePath = scanner.nextLine();
        if(inputFilePath.length() < 2 ) inputFilePath = original;

        System.out.print("Введите путь назначения: (Enter- путь по умолчанию C:\\test\\22  )  ");
        String outputFilePath = scanner.nextLine();
        if(outputFilePath.length() < 2 ) outputFilePath = copyTo;
        DirectoryCopy dc = new DirectoryCopy();
        dc.makeCopy(inputFilePath, outputFilePath);
    }

    ///-----------------------TASK__4--------------------------------
    private void task4(){
        Scanner scanner = new Scanner(System.in);

        // Ввод пути к директории и слова для поиска
        System.out.print("Введите путь к директории: ");
        String directoryPath = scanner.nextLine();
        if(directoryPath.length() <2) directoryPath = "C:\\test";

        System.out.print("Введите слово для поиска: ");
        String searchWord = scanner.nextLine();
        if(searchWord.length() <1 ) searchWord = "lorem";

        // Создание и запуск первого потока
        SearchThread searchThread = new SearchThread(directoryPath, searchWord);
        searchThread.start();

        // Создание и запуск второго потока
        ProcessThread processThread = new ProcessThread(searchThread);
        processThread.start();

        try {
            // Ожидание завершения работы первого потока
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Отображение статистики выполненных операций
        System.out.println("Статистика:");
        System.out.println("Найдено файлов с искомым словом: " + searchThread.getFoundFilesCount());
        System.out.println("Вырезано запрещенных слов: " + processThread.getForbiddenWordsCount());
    }
}
