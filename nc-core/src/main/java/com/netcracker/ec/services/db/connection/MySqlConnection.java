package com.netcracker.ec.services.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    private String url;

    public MySqlConnection() {
        this.url = "jdbc:mysql://127.0.0.1:3333/opf";
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, "opf", "opf");
    }
}
