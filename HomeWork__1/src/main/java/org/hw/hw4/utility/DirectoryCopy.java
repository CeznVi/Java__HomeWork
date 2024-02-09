package org.hw.hw4.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DirectoryCopy {

    private static int filesCopied = 0;
    private static int directoriesCreated = 0;

    public void makeCopy(String source, String destination){
        if(source.length() <= 2 || destination.length() <= 2) {
            System.out.println("Слишком короткий путь");
        }

        try {
            copyDirectory(new File(source), new File(destination));
            System.out.println("Копирование завершено");
            System.out.println("Файлов скопировано:  " + filesCopied);
            System.out.println("Директорий создано: " + directoriesCreated);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    private void copyDirectory(File sourceDir, File destinationDir) throws IOException {
        if (!sourceDir.isDirectory()) {
            System.out.println("Source is not a directory.");
            return;
        }

        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
            directoriesCreated++;
            System.out.println("Создана директория: " + destinationDir.getAbsolutePath());
        }

        File[] files = sourceDir.listFiles();
        if (files != null) {
            for (File file : files) {
                File destinationFile = new File(destinationDir, file.getName());
                if (file.isDirectory()) {
                    copyDirectory(file, destinationFile);
                } else {
                    Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    filesCopied++;
                    System.out.println("Скопирован файл: " + destinationFile.getAbsolutePath());
                }
            }
        }
    }


}
