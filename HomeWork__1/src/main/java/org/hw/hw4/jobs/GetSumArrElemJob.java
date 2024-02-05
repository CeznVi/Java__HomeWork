package org.hw.hw4.jobs;

public class GetSumArrElemJob implements Runnable{
    private final int[] array;
    private int sum;

    public GetSumArrElemJob(int[] array) {
        this.array = array;
    }

    public int GetSum(){
        return sum;
    }

    @Override
    public void run() {
        for (int num : array) {
            sum += num;
        }
    }
}
