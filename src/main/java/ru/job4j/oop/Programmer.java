package ru.job4j.oop;

public class Programmer extends Engineer{
    private String computer;

    public Programmer(String name, String surname, String education, String birthday, String tech, String computer) {
        super(name, surname, education, birthday, tech);
        this.computer = computer;
    }
    public Engineer write(Programmer technical) {
        return technical;
    }
}
