package com.codecool.stackoverflowtw.dao.model;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private String TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS questions(id INTEGER PRIMARY KEY,   " +
            "title TEXT, description TEXT, post_time TEXT, name TEXT NOT NULL)";

    private String TABLE_STATEMENT_USER = "CREATE TABLE IF NOT EXISTS users(user_id INTEGER PRIMARY KEY, name TEXT NOT NULL, password TEXT NOT NULL)";
    private SqliteConnector sqliteConnector;
    private Connection connection;

    public DatabaseManager(SqliteConnector sqliteConnector) {
        this.sqliteConnector = sqliteConnector;
        connection = sqliteConnector.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }


    public boolean executeQuery(String query) {
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }
}
