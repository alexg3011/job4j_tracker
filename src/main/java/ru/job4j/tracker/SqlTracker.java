package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement statement = cn.createStatement()) {
                String sql = "create table if not exists tracker("
                        + "id serial primary key,"
                        + "name text,"
                        + "created timestamp);";
                statement.execute(sql);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) throws SQLException {
        try (PreparedStatement statement = cn.prepareStatement(
                "insert into tracker(name, created) values (?, ?);")
        ) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.execute();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) throws SQLException {
        try (PreparedStatement statement = cn.prepareStatement(
                "update tracker "
                        + "set name = ?;")
        ) {
            statement.setString(1, item.getName());
            statement.execute();
            return true;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        try (PreparedStatement statement = cn.prepareStatement(
                "delete from tracker "
                        + "where id = ?;")
        ) {
            statement.setInt(1, id);
            statement.execute();
            return true;
        }
    }

    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from tracker;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new Item(
                            resultSet.getString("name"),
                            resultSet.getInt("id"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) throws SQLException {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "select * from tracker"
                        + " where name = ?;"
        )) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new Item(
                            resultSet.getString("name"),
                            resultSet.getInt("id"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        }
        return list;
    }

    @Override
    public Item findById(int id) throws SQLException {
        Item item;
        try (PreparedStatement statement = cn.prepareStatement(
                "select * from tracker "
                        + "where id = ?;")
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                item = new Item(
                        resultSet.getString("name"),
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("created").toLocalDateTime()
                );
            }
        }
        return item;
    }

}