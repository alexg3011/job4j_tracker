package ru.job4j.tracker.tracker;

import org.junit.*;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;
import ru.job4j.tracker.Store;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader()
                .getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items;")) {
            statement.execute();
        }
    }

    @Ignore
    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        Store tracker = new SqlTracker(connection);
        Item item = new Item("item", LocalDateTime.now());
        tracker.add(item);
        assertEquals(tracker.findById(item.getId()), item);
    }

    @Test
    public void whenTestFindById() {
        Store tracker = new SqlTracker(connection);
        Item bug = new Item("Bug", LocalDateTime.now());
        Item item = tracker.add(bug);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Store tracker = new SqlTracker(connection);
        Item first = new Item("First", LocalDateTime.now());
        Item second = new Item("Second", LocalDateTime.now());
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First", LocalDateTime.now()));
        tracker.add(new Item("Second", LocalDateTime.now()));
        tracker.add(new Item("First", LocalDateTime.now()));
        List<Item> result = tracker.findByName(first.getName());
        assertThat(result.size(), is(3));
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        Store tracker = new SqlTracker(connection);
        Item first = new Item("First", LocalDateTime.now());
        Item second = new Item("Second", LocalDateTime.now());
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First", LocalDateTime.now()));
        tracker.add(new Item("Second", LocalDateTime.now()));
        tracker.add(new Item("First", LocalDateTime.now()));
        List<Item> result = tracker.findByName(second.getName());
        assertThat(result.get(1).getName(), is(second.getName()));
    }

    @Test
    public void whenTestFindAll() {
        Store tracker = new SqlTracker(connection);
        Item first = new Item("First", LocalDateTime.now());
        Item second = new Item("Second", LocalDateTime.now());
        tracker.add(first);
        tracker.add(second);
        Item result = tracker.findAll().get(0);
        assertThat(result.getName(), is(first.getName()));
    }

    @Test
    public void whenReplace() {
        Store tracker = new SqlTracker(connection);
        Item bug = new Item("Bug", LocalDateTime.now());
        int id = tracker.add(bug).getId();
        Item bugWithDesc = new Item("Bug with description", LocalDateTime.now());
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenFalseDeleted() {
        Store tracker = new SqlTracker(connection);
        assertFalse(tracker.delete(1));
    }

    @Test
    public void whenTrueDeleted() {
        Store tracker = new SqlTracker(connection);
        Item bug = new Item("Bug", LocalDateTime.now());
        tracker.add(bug);
        assertTrue(tracker.delete(bug.getId()));
    }
}