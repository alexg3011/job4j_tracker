package ru.job4j.tracker;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateAction implements UserAction {
    private final Output out;

    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name, LocalDateTime.now());
        memTracker.add(item);
        out.println("Добавленная заявка: " + item);
        return true;
    }
}