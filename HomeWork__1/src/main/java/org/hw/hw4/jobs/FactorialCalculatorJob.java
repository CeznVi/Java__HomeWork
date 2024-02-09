package org.hw.hw4.jobs;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FactorialCalculatorJob implements Runnable{
    private final String filePath;
    private final String outputFilePath;
    private final AtomicInteger factorialCount;
    public FactorialCalculatorJob(String filePath, String outputFilePath,AtomicInteger factorialCount) {
        this.filePath = filePath;
        this.outputFilePath = outputFilePath;
        this.factorialCount = factorialCount;

    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            String line;

            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                int factorial = calculateFactorial(number);
                writer.write(factorial + "\n");
                factorialCount.incrementAndGet();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateFactorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
