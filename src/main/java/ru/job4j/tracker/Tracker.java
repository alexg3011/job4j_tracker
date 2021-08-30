package ru.job4j.tracker;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);

        return index != -1 ? items[index] : null;
    }

    public Item[] findAll() {
        Item[] newItems = new Item[size];
        for (int i = 0; i<size; i++) {
            newItems[i] = items[i];
        }
        return newItems;
    }

    public Item[] findByName(String key) {
        Item[] findElements = new Item[size];
        int j=0;
        for (int i=0; i<size; i++) {
            if (key.equals(items[i].getName())) {
                findElements[j++] = items[i];
            }
        }
        return findElements;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }



    public boolean delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            int start = index + 1;
            int distPos = index;
            int length = size - index - 1;
            System.arraycopy(items, start, items, distPos, length);
            items[size - 1] = null;
            size--;
            return true;
        }
        else {
            return false;
        }
    }
}