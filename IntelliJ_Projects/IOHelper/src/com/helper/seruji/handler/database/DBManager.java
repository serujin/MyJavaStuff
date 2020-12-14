package com.helper.seruji.handler.database;

import com.helper.seruji.data.db.DBRegister;

import java.sql.SQLException;

public class DBManager {
    public static String select(String query, DBRegister register) throws SQLException {
        return DBSelect.select(query, register);
    }

    public static void insert(String query) throws SQLException {
        DBInsert.insert(query);
    }

    public static void update(String query) throws SQLException {
        DBUpdate.update(query);
    }

    public static void delete(String query) throws SQLException {
        DBDelete.delete(query);
    }
}
