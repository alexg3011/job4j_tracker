package ru.job4j.tracker.tracker;

import org.junit.Test;
import ru.job4j.tracker.HbmTracker;
import ru.job4j.tracker.Item;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class HbmTrackerTest {
    @Test
    public void whenAdd() {
        HbmTracker hbm = new HbmTracker();
        Item item = new Item("item1", LocalDateTime.now());
        hbm.add(item);
        List<Item> all = hbm.findAll();
        assertEquals(item, all.get(0));
    }

    @Test
    public void whenReplace() {
        HbmTracker hbm = new HbmTracker();
        Item one = new Item("item1", LocalDateTime.now());
        Item two = new Item("item2", LocalDateTime.now());
        hbm.add(one);
        hbm.replace(one.getId(), two);
        assertEquals(hbm.findById(two.getId()), two);
    }

    @Test
    public void whenDelete() {
        HbmTracker hbm = new HbmTracker();
        Item one = new Item("item1", LocalDateTime.now());
        hbm.add(one);
        assertTrue(hbm.delete(one.getId()));
        assertNull(hbm.findById(one.getId()));
    }

    @Test
    public void whenFindAll() {
        HbmTracker hbm = new HbmTracker();
        Item one = new Item("item1", LocalDateTime.now());
        Item two = new Item("item2", LocalDateTime.now());
        hbm.add(one);
        hbm.add(two);
        assertEquals(List.of(one, two), hbm.findAll());
    }

    @Test
    public void whenFindByName() {
        HbmTracker hbm= new HbmTracker();
        Item one = new Item("item1", LocalDateTime.now());
        hbm.add(one);
        assertEquals(hbm.findByName("item1"), List.of(one));
    }

    @Test
    public void whenFindById() {
        HbmTracker hbm = new HbmTracker();
        Item one = new Item("item1", LocalDateTime.now());
        hbm.add(one);
        assertEquals(hbm.findById(one.getId()), one);
    }
}
