package org.hw.hw4.jobs;

public class GetAvarageInArrJob implements Runnable{
    private final int[] array;
    private double avarage;

    public GetAvarageInArrJob(int[] array) {
        this.array = array;
    }

    public double GetAvarage(){
        return avarage;
    }

    @Override
    public void run() {
        double sum = 0;
        for (int num : array) {
            sum += num;
        }
        avarage = sum / array.length;
    }
}
