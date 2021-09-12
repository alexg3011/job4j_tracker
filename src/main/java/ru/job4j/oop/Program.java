package ru.job4j.oop;

public class Program extends Programmer {

    public Program(String name, String surname,
                   String education, String birthday,
                   String tech, String computer) {
        super(name, surname, education, birthday, tech, computer);
    }

    public Programmer program(Program program) {
        return program;
    }
}
