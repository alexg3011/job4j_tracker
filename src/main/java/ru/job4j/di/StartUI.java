package ru.job4j.di;

public class StartUI {

    private Store store;

    private ConsoleInput input;

    public StartUI(Store store) {
        this.store = store;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }

    public String askStr(String question) {
        return input.askStr(question);
    }

    public int askInt(String question) {
        return input.askInt(question);
    }

}