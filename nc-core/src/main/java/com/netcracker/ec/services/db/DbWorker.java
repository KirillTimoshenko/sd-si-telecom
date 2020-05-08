package com.netcracker.ec.services.db;

import com.netcracker.ec.services.db.connection.MySqlConnection;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DbWorker {
    private final Connection connection;
    private static DbWorker dbWorker = null;

    private DbWorker() {
        try {
            this.connection = new MySqlConnection().getConnection();
        } catch (Exception e) {
            System.err.println("SQLException:\n" + e);
            throw new RuntimeException(e);
        }
    }

    public static DbWorker getInstance() {
        if (dbWorker == null) {
            dbWorker = new DbWorker();
        }
        return dbWorker;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer generateId() {
        return getIdByQuery(Queries.getQuery("get_id"));
    }

    @SneakyThrows
    public ResultSet executeSelectQuery(String query, Object... params) {
        PreparedStatement stm = prepareStatement(query, params);
        return stm.executeQuery();
    }

    @SneakyThrows
    public void executeQuery(String query, Object... params) {
        PreparedStatement stm = prepareStatement(query, params);
        stm.execute();
        stm.close();
    }

    @SneakyThrows
    public Integer getIdByQuery(String query, Object... params) {
        ResultSet resultSet = executeSelectQuery(query, params);
        if (!resultSet.next()) {
            return null;
        }
        int id = resultSet.getInt(1);
        resultSet.close();
        return id;
    }

    @SneakyThrows
    public List<Integer> getIdsByQuery(String query, Object... params) {
        ResultSet resultSet = executeSelectQuery(query, params);
        List<Integer> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }
        resultSet.close();
        return ids;
    }

    @SneakyThrows
    public String getStringValueByQuery(String query, Object... params) {
        ResultSet resultSet = executeSelectQuery(query, params);
        if (!resultSet.next()) {
            return null;
        }
        String stringValue = resultSet.getString(1);
        resultSet.close();
        return stringValue;
    }

    private PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof Integer) {
                stm.setInt(i + 1, (Integer) params[i]);
            } else {
                stm.setString(i + 1, (String) params[i]);
            }
        }
        return stm;
    }
}
