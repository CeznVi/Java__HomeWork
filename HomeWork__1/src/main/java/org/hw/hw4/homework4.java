package org.hw.hw4;

import org.hw.hw4.jobs.GenerateRndIntArrJob;
import org.hw.hw4.jobs.GetAvarageInArrJob;
import org.hw.hw4.jobs.GetSumArrElemJob;

import java.lang.reflect.Array;
import java.util.Random;

public class homework4 implements Runnable{

    @Override
    public void run() {

        ///-----------------------TASK__1
        task1();

        ////-----------------------TASK__2
        ///task2();

        ////-----------------------TASK__3
        //task3();

        ////-----------------------TASK__4
        ///task4();
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



}
