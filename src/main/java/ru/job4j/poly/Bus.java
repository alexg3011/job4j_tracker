package ru.job4j.poly;

public class Bus implements Transport, Vehicle {
    @Override
    public void run() {
        System.out.println("Еду");
    }

    @Override
    public void people(int count) {
        System.out.println("Количество пассажиров: " + count);
    }

    @Override
    public double fill(double litres) {
        return 45.5 * litres;
    }

    @Override
    public void move() {
        System.out.println("Автобус едет по шоссе");
    }
}
