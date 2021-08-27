package ru.job4j.oop;

public class Surgeon extends Doctor{
    private String knife;
    public Surgeon(String name) {
        super(name);
    }
    public Surgeon(String name, String surname, String education, String birthday, String knife){
        super(name, surname, education, birthday);
        this.knife = knife;
    }
    public String teach(Surgeon knife) {
        return this.knife;
    }
}
