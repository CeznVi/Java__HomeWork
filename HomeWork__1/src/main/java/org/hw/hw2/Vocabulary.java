package org.hw.hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Vocabulary {
    public Vocabulary() {
        dictionary.put("hi", "привет");
        counterWord.put("hi", counterWord.getOrDefault("hi", 0) + 3);

        dictionary.put("dog", "собака");
        counterWord.put("dog", counterWord.getOrDefault("dog", 0) + 2);

        dictionary.put("zero", "ноль");
        counterWord.put("zero", counterWord.getOrDefault("zero", 0) + 2);
    }

    private HashMap<String, Integer> counterWord = new HashMap<String, Integer>();
    private HashMap<String, String> dictionary = new HashMap<String, String>();

    ////---------------------Методы-----------------------------
    public void runProgram(){
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Показать словарь");
            System.out.println("2. Добавить/изменить перевод");
            System.out.println("3. Добавить/изменить слово");
            System.out.println("4. Удалить перевод");
            System.out.println("5. Удалить слово");
            System.out.println("6. ТОП10 популярных слов");
            System.out.println("7. ТОП10 непопулярных слов");
            System.out.println("8. Выход");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    showAllWord();
                    break;
                case 2:
                    addOrUpdateTranslation();
                    break;
                case 3:
                    addOrUpdateWord();
                    break;
                case 4:
                    deleteTranslation();
                    break;
                case 5:
                    deleteWord();
                    break;
                case 6:
                    showPopularWords();
                    break;
                case 7:
                    showNoPopularWords();
                    break;
                case 8:
                    System.out.println("Программа завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().trim();
    }

    private void showAllWord() {
        for (Map.Entry<String, String> entry: dictionary.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    private void addOrUpdateTranslation() {
        System.out.println("Введите слово:");
        String word = getInput();

        System.out.println("Введите перевод:");
        String translation = getInput();

        dictionary.put(word, translation);
        counterWord.put(word, counterWord.getOrDefault(word, 0) + 1);

        System.out.println("Добавление/изменение перевода прошла успешно!");

    }

    private void addOrUpdateWord() {
        System.out.println("Введите слово:");
        String word = getInput();

        System.out.println("Введите перевод:");
        String translation = getInput();

        dictionary.put(word, translation);
        counterWord.put(word, counterWord.getOrDefault(word, 0) + 1);

        System.out.println("Добавление/изменение слова прошла успешно!");
    }

    private void deleteTranslation() {
        System.out.println("Введите слово, у которого нужно удалить перевод:");
        String removerTranslation = getInput();

        if (dictionary.containsKey(removerTranslation)) {
            dictionary.remove(removerTranslation);
            System.out.println("Перевод успешно удален.");
        } else {
            System.out.println("Перевод не найдено в словаре.");
        }
    }

    private void deleteWord() {
        System.out.println("Введите слово, у которого нужно удалить слово:");
        String removerWord = getInput();

        if (dictionary.containsKey(removerWord)) {
            dictionary.remove(removerWord);
            counterWord.remove(removerWord);
            System.out.println("Слово успешно удалено из словаря.");
        } else {
            System.out.println("Слово не найдено в словаре.");
        }
    }

    private void showPopularWords() {
        System.out.println("ТОП10 популярных слов:");
        counterWord.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey() + "-" + entry.getValue()));
    }

    private void showNoPopularWords() {
        System.out.println("ТОП10 непопулярных слов:");
        counterWord.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry1.getValue(), entry2.getValue()))
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey() + "-" + entry.getValue()));
    }
}
