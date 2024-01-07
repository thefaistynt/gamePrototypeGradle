package org.example.DBSObjects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class DBCClass<T> {
    private final Connection connection;

    public DBCClass(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public abstract List<T> getAll() throws SQLException;
}
