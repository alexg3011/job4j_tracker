package ru.job4j.poly;

public interface Transport {
    void run();

    void people(int count);

    double fill(double litres);
}