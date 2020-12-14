package com.helper.seruji.handler.database;

import com.helper.seruji.handler.database.DBConnection;

import java.sql.SQLException;

public class DBUpdate {
    //MAYBE CHANGE THIS TO BE MORE DECENT
    protected static void update(String query) throws SQLException {
        DBConnection.getInstance().getConnection().createStatement().executeUpdate(query);
    }
}
