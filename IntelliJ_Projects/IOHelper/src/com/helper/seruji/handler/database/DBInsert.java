package com.helper.seruji.handler.database;

import com.helper.seruji.handler.database.DBConnection;

import java.sql.SQLException;

public class DBInsert {
    //MAYBE CHANGE THIS TO BE MORE DECENT
    protected static void insert(String query) throws SQLException {
        DBConnection.getInstance().getConnection().createStatement().executeUpdate(query);
    }
}
