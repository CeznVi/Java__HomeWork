package org.hw.hw4.jobs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProcessThread extends Thread{
    private SearchThread searchThread;
    private int forbiddenWordsCount;

    public ProcessThread(SearchThread searchThread) {
        this.searchThread = searchThread;
        this.forbiddenWordsCount = 0;
    }

    public int getForbiddenWordsCount() {
        return forbiddenWordsCount;
    }

    @Override
    public void run() {
        try {
            // Ожидание завершения работы первого потока
            searchThread.join();

            // Получение списка запрещенных слов из файла
            List<String> forbiddenWords = Files.readAllLines(Paths.get(searchThread.directoryPath+"\\forbidden_words.txt"));

            // Объединение содержимого файлов
            List<String> combinedContent = new ArrayList<>();
            Files.walk(Paths.get(searchThread.directoryPath))
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            combinedContent.addAll(Files.readAllLines(filePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            // Вырезание запрещенных слов
            List<String> processedContent = new ArrayList<>();
            for (String line : combinedContent) {
                for (String word : line.split("\\s+")) {
                    if (!forbiddenWords.contains(word)) {
                        processedContent.add(word);
                    } else {
                        forbiddenWordsCount++;
                    }
                }
            }

            // Запись обработанного содержимого в файл
            Files.write(Paths.get("processed_content.txt"), processedContent);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
