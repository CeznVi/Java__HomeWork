package org.hw.hw4.jobs;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeFinderJob implements Runnable{
    private final String filePath;
    private final String outputFilePath;
    private final AtomicInteger primeCount;


    public PrimeFinderJob(String filePath, String outputFilePath, AtomicInteger primeCount) {
        this.filePath = filePath;
        this.outputFilePath = outputFilePath;
        this.primeCount = primeCount;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (isPrime(number)) {
                    writer.write(number + "\n");
                    primeCount.incrementAndGet();
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

}
