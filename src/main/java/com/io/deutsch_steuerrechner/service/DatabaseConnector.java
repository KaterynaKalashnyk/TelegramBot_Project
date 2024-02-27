package com.io.deutsch_steuerrechner.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Deutschsteuerrechner";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kkattka27092005";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

