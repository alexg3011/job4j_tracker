package ru.job4j.tracker.tracker;

import org.junit.Test;
import ru.job4j.tracker.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() throws SQLException {
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker memTracker = new MemTracker();
        Output out = new StubOutput();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(out),
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() throws SQLException {
        MemTracker memTracker = new MemTracker();
        Output out = new StubOutput();
        Item item = memTracker.add(new Item("Replaced item", LocalDateTime.now()));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(out),
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() throws SQLException {
        MemTracker memTracker = new MemTracker();
        Output out = new StubOutput();
        Item item = memTracker.add(new Item("Deleted item", LocalDateTime.now()));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(out),
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowAllAction() throws SQLException {
        MemTracker memTracker = new MemTracker();
        Output out = new StubOutput();
        Item item = new Item("first", LocalDateTime.now());
        memTracker.add(item);
        Item item1 = new Item("second", LocalDateTime.now());
        memTracker.add(item1);
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ShowAllItems(out),
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Show all items ====" + System.lineSeparator()
                + item + System.lineSeparator()
                + item1 + System.lineSeparator()
                + "Menu." + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByName() throws SQLException {
        MemTracker memTracker = new MemTracker();
        Output out = new StubOutput();
        Item item = new Item("first", LocalDateTime.now());
        memTracker.add(item);
        Item item1 = new Item("second", LocalDateTime.now());
        memTracker.add(item1);
        Input in = new StubInput(
                new String[] {"0", item1.getName(), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemByNameAction(out),
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Find items by name ====" + System.lineSeparator()
                + item1 + System.lineSeparator()
                + "Menu." + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByID() throws SQLException {
        MemTracker memTracker = new MemTracker();
        Output out = new StubOutput();
        Item item = new Item("first", LocalDateTime.now());
        memTracker.add(item);
        Item item1 = new Item("second", LocalDateTime.now());
        memTracker.add(item1);
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemByIdAction(out),
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
                + "=== Find item by id ====" + System.lineSeparator()
                + item + System.lineSeparator()
                + "Menu." + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"2", "0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new ExitAction()
        );
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu." + ln
                                + "0. Exit" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu." + ln
                                + "0. Exit" + ln
                )
        );
    }
}