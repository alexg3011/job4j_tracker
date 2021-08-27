package ru.job4j.inheritance;

public class JSONReport extends TextReport{
    @Override
    public String generate(String name, String body) {
        return String.format("{%s" +
                " \"%s\" : \"%s\"%s," +
                " \"%s\" : \"%s\" %s" +
                "}", System.lineSeparator(), name, name, System.lineSeparator(), body, body, System.lineSeparator());
    }
}
