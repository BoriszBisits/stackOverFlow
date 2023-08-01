package com.codecool.stackoverflowtw.dao.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnector {

    private final String dbFile;

    public SqliteConnector(String dbFile) {
        this.dbFile = dbFile;
    }

    public Connection getConnection() {
        Connection conn;
        try {
            String url = "jdbc:sqlite:" + dbFile;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            return conn;

        } catch (Exception e) {
            System.out.println("here" + e.getMessage());
            return null;
        }


    }
}
