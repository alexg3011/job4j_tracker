package ru.job4j.oop;

public class Builder extends Engineer {
    private final String hummer;

    public Builder(String name,
                   String surname,
                   String education,
                   String birthday,
                   String tech,
                   String hummer) {
        super(name, surname, education, birthday, tech);
        this.hummer = hummer;
    }

    public Engineer draw(Builder plan) {
        return plan;
    }
}
