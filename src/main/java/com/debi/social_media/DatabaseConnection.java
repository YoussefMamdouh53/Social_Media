package com.debi.social_media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection
{
    private static final String DB_URL = "jdbc:mysql://localhost/social_media";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
