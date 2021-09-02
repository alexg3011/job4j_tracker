package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        /* Добавим в tracker новую заявку */
        Item item = tracker.add(new Item("Deleted item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        Input in = new StubInput(
                new String[] {"0" , String.valueOf(item.getId()),"1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowAllAction() {
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        Item item = new Item("first");
        tracker.add(item);
        Item item1 = new Item("second");
        tracker.add(item1);
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        UserAction[] actions = {
                new ShowAllItems(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator() +
                "0. Show all items" + System.lineSeparator() +
                "1. Exit" + System.lineSeparator() +
                "=== Show all items ====" + System.lineSeparator() +
                item + System.lineSeparator() +
                item1 + System.lineSeparator() +
                "Menu." + System.lineSeparator() +
                "0. Show all items" + System.lineSeparator() +
                "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        Item item = new Item("first");
        tracker.add(item);
        Item item1 = new Item("second");
        tracker.add(item1);
        Input in = new StubInput(
                new String[] {"0", item1.getName(), "1"}
        );
        UserAction[] actions = {
                new FindItemByNameAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator() +
                "0. Find items by name" + System.lineSeparator() +
                "1. Exit" + System.lineSeparator() +
                "=== Find items by name ====" + System.lineSeparator() +
                item1 + System.lineSeparator() +
                "Menu." + System.lineSeparator() +
                "0. Find items by name" + System.lineSeparator() +
                "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByID() {
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        Item item = new Item("first");
        tracker.add(item);
        Item item1 = new Item("second");
        tracker.add(item1);
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new FindItemByIdAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator() +
                "0. Find item by id" + System.lineSeparator() +
                "1. Exit" + System.lineSeparator() +
                "=== Find item by id ====" + System.lineSeparator() +
                item + System.lineSeparator() +
                "Menu." + System.lineSeparator() +
                "0. Find item by id" + System.lineSeparator() +
                "1. Exit" + System.lineSeparator()
        ));
    }
}