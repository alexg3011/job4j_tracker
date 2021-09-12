package ru.job4j.oop;

public class Dentist extends Doctor {
    private final String scalpel;

    public Dentist(String name, String surname, String education, String birthday, String scalpel) {
        super(name, surname, education, birthday);
        this.scalpel = scalpel;
    }

    public Builder pull(Builder tooth) {
        return tooth;
    }
}
