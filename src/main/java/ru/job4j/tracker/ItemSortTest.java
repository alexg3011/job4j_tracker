package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemSortTest {
    @Test
    public void ItemSorterByIdTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item1", 3));
        list.add(new Item("item1", 2));
        list.add(new Item("item1", 1));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item1", 1));
        sortedList.add(new Item("item1", 2));
        sortedList.add(new Item("item1", 3));
        list.sort(new ItemSorterById());
        assertThat(sortedList, is(list));
    }

    @Test
    public void ItemSorterByNameTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item3", 1));
        list.add(new Item("item2", 1));
        list.add(new Item("item1", 1));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item1", 1));
        sortedList.add(new Item("item2", 1));
        sortedList.add(new Item("item3", 1));
        list.sort(new ItemSorterByName());
        assertThat(sortedList, is(list));
    }

    @Test
    public void ItemReverseByIdTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item1", 2));
        list.add(new Item("item1", 1));
        list.add(new Item("item1", 3));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item1", 3));
        sortedList.add(new Item("item1", 2));
        sortedList.add(new Item("item1", 1));
        list.sort(new ItemReversById());
        assertThat(sortedList, is(list));
    }

    @Test
    public void ItemReverseByNameTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("item2", 1));
        list.add(new Item("item3", 1));
        list.add(new Item("item1", 1));
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(new Item("item3", 1));
        sortedList.add(new Item("item2", 1));
        sortedList.add(new Item("item1", 1));
        list.sort(new ItemReversByName());
        assertThat(sortedList, is(list));
    }
}
