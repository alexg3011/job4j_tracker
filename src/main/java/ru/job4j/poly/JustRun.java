package ru.job4j.poly;

public class JustRun {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[]{new Bus(), new Plane(), new Train()};
        for (Vehicle v : vehicles) {
            v.move();
        }
    }
}
