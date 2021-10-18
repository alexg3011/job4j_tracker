package ru.job4j.tracker.tracker;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenTestFindById() {
        MemTracker memTracker = new MemTracker();
        Item bug = new Item("Bug", LocalDateTime.now());
        Item item = memTracker.add(bug);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindAll() {
        MemTracker memTracker = new MemTracker();
        Item first = new Item("First", LocalDateTime.now());
        Item second = new Item("Second", LocalDateTime.now());
        memTracker.add(first);
        memTracker.add(second);
        Item result = memTracker.findAll().get(0);
        assertThat(result.getName(), is(first.getName()));
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        MemTracker memTracker = new MemTracker();
        Item first = new Item("First", LocalDateTime.now());
        Item second = new Item("Second", LocalDateTime.now());
        memTracker.add(first);
        memTracker.add(second);
        memTracker.add(new Item("First", LocalDateTime.now()));
        memTracker.add(new Item("Second", LocalDateTime.now()));
        memTracker.add(new Item("First", LocalDateTime.now()));
        List<Item> result = memTracker.findByName(first.getName());
        assertThat(result.size(), is(3));
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        MemTracker memTracker = new MemTracker();
        Item first = new Item("First", LocalDateTime.now());
        Item second = new Item("Second", LocalDateTime.now());
        memTracker.add(first);
        memTracker.add(second);
        memTracker.add(new Item("First", LocalDateTime.now()));
        memTracker.add(new Item("Second", LocalDateTime.now()));
        memTracker.add(new Item("First", LocalDateTime.now()));
        List<Item> result = memTracker.findByName(second.getName());
        assertThat(result.get(1).getName(), is(second.getName()));
    }

    @Test
    public void whenReplace() {
        MemTracker memTracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        memTracker.replace(id, bugWithDesc);
        assertThat(memTracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        MemTracker memTracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        memTracker.delete(id);
        assertNull(memTracker.findById(id));
    }
}
