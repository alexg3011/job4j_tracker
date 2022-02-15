package ru.job4j.tracker;

import ru.job4j.react.Observe;

public interface ReactStore {
    void findAll(Observe<Item> observe);
}
