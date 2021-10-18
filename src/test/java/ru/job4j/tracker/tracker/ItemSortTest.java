package ru.job4j.tracker.tracker;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tracker.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemSortTest {
    @Test
    public void itemSorterByIdTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item1", 3, LocalDateTime.now()));
        list.add(new Item("item1", 2, LocalDateTime.now()));
        list.add(new Item("item1", 1, LocalDateTime.now()));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item1", 1, LocalDateTime.now()));
        sortedList.add(new Item("item1", 2, LocalDateTime.now()));
        sortedList.add(new Item("item1", 3, LocalDateTime.now()));
        list.sort(new ItemSorterById());
        assertThat(sortedList, is(list));
    }

    @Test
    public void itemSorterByNameTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item3", 1, LocalDateTime.now()));
        list.add(new Item("item2", 1, LocalDateTime.now()));
        list.add(new Item("item1", 1, LocalDateTime.now()));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item1", 1, LocalDateTime.now()));
        sortedList.add(new Item("item2", 1, LocalDateTime.now()));
        sortedList.add(new Item("item3", 1, LocalDateTime.now()));
        list.sort(new ItemSorterByName());
        assertThat(sortedList, is(list));
    }

    @Test
    public void itemReverseByIdTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item1", 2, LocalDateTime.now()));
        list.add(new Item("item1", 1, LocalDateTime.now()));
        list.add(new Item("item1", 3, LocalDateTime.now()));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item1", 3, LocalDateTime.now()));
        sortedList.add(new Item("item1", 2, LocalDateTime.now()));
        sortedList.add(new Item("item1", 1, LocalDateTime.now()));
        list.sort(new ItemReversById());
        assertThat(sortedList, is(list));
    }

    @Test
    public void itemReverseByNameTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item2", 1, LocalDateTime.now()));
        list.add(new Item("item3", 1, LocalDateTime.now()));
        list.add(new Item("item1", 1, LocalDateTime.now()));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item3", 1, LocalDateTime.now()));
        sortedList.add(new Item("item2", 1, LocalDateTime.now()));
        sortedList.add(new Item("item1", 1, LocalDateTime.now()));
        list.sort(new ItemReversByName());
        assertThat(sortedList, is(list));
    }
}
