package org.hw.hw2;
import java.util.*;

public class TaxOfficeDataBase {

    private HashMap<Integer, Human> dataBase = new HashMap<Integer, Human>();

    private void showDatabase() {
        dataBase.entrySet().forEach(System.out::println);
    }

    private void showDataById() {
        System.out.println("Введите идентификатор:");
        Integer code = Integer.valueOf(getInput());

        if (dataBase.containsKey(code)) {
            System.out.println(dataBase.get(code));
            System.out.println("Распечатка данных по конкретному коду выполнена.");
        } else {
            System.out.println("Введенный данные не существуют в системе.");
        }
    }

    private void showDataByTypeOfPenalties() {
        System.out.println("Введите тип штрафа:");
        String typeOfPenalties = getInput();

        dataBase.entrySet()
                .stream()
                .filter(entry -> entry.getValue()
                        .getTypeOfPenalties()
                        .equals(typeOfPenalties))
                .forEach(System.out::println);

        System.out.println("Распечатка данных по конкретному типу штрафа выполнена.");
    }

    private void showDataByCity() {
        System.out.println("Введите город:");
        String city = getInput();

        dataBase.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getCity().equals(city))
                .forEach(System.out::println);

        System.out.println("Распечатка данных по конкретному городу выполнена.");
    }

    private void addHuman() {
        System.out.println("Введите идентификатор:");
        Integer code = Integer.valueOf(getInput());

        System.out.println("Введите имя человека:");
        String name = getInput();

        System.out.println("Введите город:");
        String city = getInput();

        System.out.println("Введите тип штрафа:");
        String typeOfPenalties = getInput();

        System.out.println("Введите штраф:");
        String penalty = getInput();

        List<String> penalties = new ArrayList<String>();
        penalties.add(penalty);

        Human human = new Human();
        human.setName(name);
        human.setCity(city);
        human.setTypeOfPenalties(typeOfPenalties);
        human.setPenalties(penalties);

        dataBase.put(code, human);
        System.out.println("Операция добавление нового человека с информацией о нем прошла успешно.");
    }

    private void addHumanPenalties() {
        System.out.println("Введите идентификатор:");
        Integer code = Integer.valueOf(getInput());

        if (dataBase.containsKey(code)) {
            System.out.println("Введите штраф:");
            String penalty = getInput();
            dataBase.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().getPenalties().add(penalty))
                    .forEach(System.out::println);
            System.out.println("Добавление новых штрафов для существующей записи выполнена.");
        } else {
            System.out.println("Введенные данные не существуют.");
        }
    }

    private void deleteById() {
        System.out.println("Введите идентификатор:");
        Integer code = Integer.valueOf(getInput());

        if (dataBase.containsKey(code)) {
            dataBase.remove(code);
            System.out.println("Удаление штрафа выполнена.");
        } else {
            System.out.println("Введенные данные не существуют.");
        }
    }

    private void changeInfo() {
        System.out.println("Введите идентификатор:");
        Integer code = Integer.valueOf(getInput());

        if (dataBase.containsKey(code)) {

            System.out.println("Введите имя человека:");
            String name = getInput();

            System.out.println("Введите город:");
            String city = getInput();

            System.out.println("Введите тип штрафа:");
            String typeOfPenalties = getInput();

            System.out.println("Введите штраф:");
            String penalty = getInput();

            List<String> penalties = new ArrayList<String>();
            penalties.add(penalty);

            Human human = dataBase.get(code);
            human.setName(name);
            human.setCity(city);
            human.setTypeOfPenalties(typeOfPenalties);
            human.setPenalties(penalties);

            System.out.println("Замена информации о человеке и его штрафах выполнена.");

        } else {
            System.out.println("Введенные данные не существуют.");
        }
    }

    private String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().trim();
    }

    public void runProgram() {
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Полная распечатка базы данных");
            System.out.println("2. Распечатка данных по конкретному коду");
            System.out.println("3. Распечатка данных по конкретному типу штрафа");
            System.out.println("4. Распечатка данных по конкретному городу");
            System.out.println("5. Добавление нового человека с информацией о нем");
            System.out.println("6. Добавление новых штрафов для существующей записи");
            System.out.println("7. Удаление штрафа");
            System.out.println("8. Замена информации о человеке и его штрафах");
            System.out.println("9. Выход");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    showDatabase();
                    break;
                case 2:
                    showDataById();
                    break;
                case 3:
                    showDataByTypeOfPenalties();
                    break;
                case 4:
                    showDataByCity();
                    break;
                case 5:
                    addHuman();
                    break;
                case 6:
                    addHumanPenalties();
                    break;
                case 7:
                    deleteById();
                    break;
                case 8:
                    changeInfo();
                    break;
                case 9:
                    System.out.println("Программа завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
