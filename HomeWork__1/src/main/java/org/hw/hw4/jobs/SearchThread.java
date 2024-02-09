package org.hw.hw4.jobs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SearchThread extends Thread {
    public String directoryPath;
    private String searchWord;
    private int foundFilesCount;

    public SearchThread(String directoryPath, String searchWord) {
        this.directoryPath = directoryPath;
        this.searchWord = searchWord;
        this.foundFilesCount = 0;
    }

    public int getFoundFilesCount() {
        return foundFilesCount;
    }

    @Override
    public void run() {
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            List<String> lines = Files.readAllLines(filePath);
                            for (String line : lines) {
                                if (line.contains(searchWord)) {
                                    synchronized (this) {
                                        foundFilesCount++;
                                        break;
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
