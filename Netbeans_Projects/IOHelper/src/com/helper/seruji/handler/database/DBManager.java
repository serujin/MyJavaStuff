package com.helper.seruji.handler.database;

import com.helper.seruji.data.db.DBProvider;
import com.helper.seruji.data.db.DBRegister;

import java.sql.SQLException;

public class DBManager {    
    public DBManager(DBProvider provider, String dbName, String user, String password) throws SQLException, ClassNotFoundException {
        switch(provider) {
            case MYSQL:
                DBConnection.getInstance().connectToMySQL(dbName, user, password);
                break;
//            case SQLSERVER: TODO: add support to sqlserver databases
//                DBConnection.getInstance().connectToSQLServer(dbName, user, password);
//                break;
        }
    }
    
    public String select(String query, DBRegister register) throws SQLException {
        return DBSelect.select(query, register);
    }

    public void insert(String query) throws SQLException {
        DBInsert.insert(query);
    }

    public void update(String query) throws SQLException {
        DBUpdate.update(query);
    }

    public void delete(String query) throws SQLException {
        DBDelete.delete(query);
    }
}
