package ru.job4j.tracker;

import java.time.LocalDateTime;

public class GCtest {
    public static void main(String[] args) {
        MemTracker memTracker = new MemTracker();
        while (true) {
            memTracker.add(new Item("name", 1, LocalDateTime.now()));
            memTracker.findById(1);
            memTracker.delete(1);
        }
    }
}
