package org.hw.hw2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.*;

public class SimulatorSeaBoatStation {
    private Random random = new Random();

    ////Пассажировместимость одной лодки
    private final int PASSENGER_CAPACITY_BOAT = 45;
    ////Продолжительность цикла симуляции. 1 сутки = 60мин * 24час = 1440минут.
    private final int SIMULATION_TIME  = 1440;
    ////Текущее время симуляции в минутах.
    private int currTime = 0;
    ////Максимальное количество людей на остановке
    private final int MAX_PEOPLE_AT_STATION = 45;
    //// Очередь на остановке из ожидающих людей
    ArrayDeque<People> peopleQueue = new ArrayDeque<People>();
    ////Ночной период 0-240 мин(с 0 до 4)
    private final int NIGHT_TIME = 240;
    ////Утренний период 240--600 мин (с 4 до 10)
    private final int MORNING_TIME = 600;
    ////Дневной период 600--1080 мин (с 10 до 18)
    private final int DAY_TIME = 1080;
    ///Для вечернего времени не заводим переменную, считаем вечернее время с 18--24.
    ///Список, который хранит среднее время ожидания пассажиров
    private ArrayList<Integer> listAvarageTimePas = new ArrayList<Integer>();
    ///Среднее время ожидания людей на остановке
    private double avarageTime = 0;
    ///Средняя количество мест в катерах прибывавших на остановку
    private double avarageFreeSpaceBoat = 0;
    ///Список, который хранит количество свободных мест в прибывшем катере на остановку
    private ArrayList<Integer> listFreeSpaceBoat = new ArrayList<Integer>();
    private boolean isTrueNightTime = true;
    private boolean isTrueMorningTime = true;
    private boolean isTrueDayTime = true;

    ////Количество пассажиров которые стояли в очереди
    private int countPassagerInQuenq = 0;
    ////Количество пассажиров которые сели на лодку
    private int countPassagerLoadOnBoat = 0;


    /// --------------- МЕТОДЫ ---------------------------------------------------------------------
    public void runSimulation(){

        while (currTime < SIMULATION_TIME){

            ////Определение какой сейчас период времени(для дальнейших вычислений)
            if(currTime <= NIGHT_TIME){
                ///НОЧНОЕ ВРЕМЯ С 0--4
                ////Допускаем что пассажиры приходят в это время с интервалом в 120 минут
                arrivePassenger(120, 0, 2);
                ////Допускаем что катер ходит с интервалом в 120 мин
                arriveBoat(120);
                if(peopleQueue.size() > MAX_PEOPLE_AT_STATION) { isTrueNightTime = false; }

            } else if (currTime > NIGHT_TIME && currTime <= MORNING_TIME) {
                ///Утренний период с 4 до 10

                ////Допускаем что пассажиры приходят в это время с интервалом в 5 минут
                arrivePassenger(6, 3, 8);

                ////Допускаем что катер ходит с интервалом в 20мин
                arriveBoat(20);
                if(peopleQueue.size() > MAX_PEOPLE_AT_STATION) { isTrueMorningTime = false; }

            } else if(currTime > MORNING_TIME && currTime <= DAY_TIME) {
                ////Дневной период с 10 до 18

                ////Допускаем что пассажиры приходят в это время с интервалом в 5 минут
                arrivePassenger(10, 3, 10);
                ////Допускаем что катер ходит с интервалом в 15мин
                arriveBoat(15);
                if(peopleQueue.size() > MAX_PEOPLE_AT_STATION) { isTrueDayTime = false; }

            } else {
                ///НОЧНОЕ ВРЕМЯ с 18 -- 24

                ////Допускаем что пассажиры приходят в это время с интервалом в 50 минут
                arrivePassenger(20, 2, 8);
                ////Допускаем что катер ходит с интервалом в 40мин
                arriveBoat(40);
                if(peopleQueue.size() > MAX_PEOPLE_AT_STATION) { isTrueNightTime = false; }
            }
            currTime++;
        }
        avarageTime =  IntStream.of(listAvarageTimePas.stream().mapToInt(i -> i).toArray()).average().getAsDouble();
        avarageFreeSpaceBoat = IntStream.of(listFreeSpaceBoat.stream().mapToInt(i -> i).toArray()).average().getAsDouble();


        System.out.println("----------------------------------------");
        System.out.println("Максимальное количество мест в лодке = " + PASSENGER_CAPACITY_BOAT);
        System.out.println("Допустимое количество людей ожидающих лодку = " + MAX_PEOPLE_AT_STATION);
        System.out.println("----------------Общие данные--------------");
        System.out.println("Количество людей которые пришли на остановку = " + countPassagerInQuenq);
        System.out.println("Количество людей которые уплыли на лодке = " + countPassagerLoadOnBoat);
        System.out.println("Процент людей которые смогли уплыть на лодке с этой остановки = " + (countPassagerLoadOnBoat*100/countPassagerInQuenq) + "%");
        System.out.println("Среднее количество свободных мест в катерах прибывших на остановку = " + String.format("%.0f", avarageFreeSpaceBoat));
        System.out.println("Среднее время пребывание на остановке = " + String.format("%.2f", avarageTime) + " минут");
        System.out.println("----------------Правильность интервалов--------------");
        System.out.println("--Ночной период с 0 до 4 && 18 до 24 = " + isTrueNightTime);
        System.out.println("--Утренний период с 4 до 10 = " + isTrueMorningTime);
        System.out.println("--Дневной период с 10 до 18 = " + isTrueDayTime);

    }

    ///Метод добавление пассажиров в очередь, принимает среднее время прибытия на остановку
    private void arrivePassenger(int avarageTimeArrivePasser, int minPass, int maxPass){
        ///Если среднее время прибытие пассажира подходит
        if(currTime % avarageTimeArrivePasser == 0) {

            ///Генерируем случайное количество пассажиров в указанном диапазоне
            int passRandomCount = random.nextInt((maxPass - minPass) + 1) + minPass;
            countPassagerInQuenq += passRandomCount;

            for(int i = 0; i < passRandomCount; i++) {
                peopleQueue.add(new People(currTime));
            }
        }
    }

    ///Метод эмуляции прибытия катера на остановку, принемает среднее время прибытия катера на остановку
    private void arriveBoat(int avarageTimeArriveBoat) {
        ///Если среднее время прибытие катера подходит
        if(currTime % avarageTimeArriveBoat == 0) {
            int countFreePlaces = random.nextInt((PASSENGER_CAPACITY_BOAT));

            listFreeSpaceBoat.add(countFreePlaces);

            if(countFreePlaces == 0) return;

            ////Для исключения исключительных ситуаций в симуляции
            if(countFreePlaces > peopleQueue.size()) {
                countFreePlaces = peopleQueue.size();
            }

            People p;

            for(int i = 0; i < countFreePlaces; i++) {
                p = peopleQueue.pollFirst();
                mathAvarageTimeWaitingPassager(p.arriveTime, currTime);
                countPassagerLoadOnBoat++;
            }

        }
    }



    ///Добавление в список времени ожидания человека на остановке (время посадки - время прихода на остановку = времени ожидания)
    private void mathAvarageTimeWaitingPassager(int timeStart, int timeFinaly){
        listAvarageTimePas.add(timeFinaly - timeStart);
    }



}

