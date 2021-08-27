package ru.job4j.oop;

public class Engineer extends Profession{
    private String tech;
    public Engineer(String name, String surname, String education, String birthday, String tech) {
        super(name, surname, education, birthday);
        this.tech = tech;
    }
}
