package ru.job4j.tracker.tracker;
import org.junit.Test;
import ru.job4j.tracker.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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


    @Test
    public void execute() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item());
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ====" + ln + "Заявка изменена успешно." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteAction() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item());
        tracker.add(new Item());
        DeleteAction del = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        del.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ====" + ln + "Заявка удалена успешно." + ln));
        assertNull(tracker.findAll().get(0).getName());
    }

    @Test
    public void whenFindByIdAction() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("falseItem", LocalDateTime.now()));
        tracker.add(new Item("FindItem", LocalDateTime.now()));
        Item item = tracker.findById(1);
        String itemName = "FindItem";
        FindItemByIdAction fbid = new FindItemByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        fbid.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ====" + ln + item + ln));
        assertThat(tracker.findById(2).getName(), is(itemName));
    }

    @Test
    public void whenFindByNameAction() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("falseItem", LocalDateTime.now()));
        Item item = new Item("FindItem", LocalDateTime.now());
        tracker.add(item);
        String itemName = "FindItem";
        FindItemByIdAction fbid = new FindItemByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(2);

        fbid.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ====" + ln + item + ln));
        assertThat(tracker.findById(2).getName(), is(itemName));
    }
}
