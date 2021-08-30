package ru.job4j.poly;

public class Bus implements Transport,Vehicle{
    @Override
    public void run() {

    }

    @Override
    public void people(int count) {

    }

    @Override
    public double fill(double litres) {
        return 0;
    }

    @Override
    public void move() {
        System.out.println("Автобус едет по шоссе");
    }
}
