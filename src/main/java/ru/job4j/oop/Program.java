package ru.job4j.oop;

public class Program extends Programmer{
    public Program(String name) {
        super(name);
    }

    public Programmer program(Program program) {
        return program;
    }
}
