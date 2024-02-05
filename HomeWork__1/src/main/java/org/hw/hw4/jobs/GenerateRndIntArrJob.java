package org.hw.hw4.jobs;

import java.util.Random;

/**
 * Класс, который генерирует массив случайных чисел в массив полученный в конструкторе класса
 */
public class GenerateRndIntArrJob implements Runnable{
    private final int[] array;


    /**
     * Конструктор класса
     * @param array массив в который будут сгенерированы случайные числа
     */
    public GenerateRndIntArrJob(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt((100 - 1) + 1) + 1;
        }
    }

}
