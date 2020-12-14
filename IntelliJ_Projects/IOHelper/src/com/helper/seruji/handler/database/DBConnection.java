package com.helper.seruji.handler.database;

import com.helper.seruji.data.db.DBProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String SQLSERVER_CONNECTION = "jdbc:sqlserver://localhost:1433;databaseName=%1$s;user=%2$s;password=%3$s;";
    private static final String MYSQL_CONNECTION = "jdbc:mysql://localhost/%1$";

    private static DBConnection instance;

    private Connection connection;
    private DBProvider provider;

    public static DBConnection getInstance() {
        if(DBConnection.instance == null) {
            DBConnection.instance = new DBConnection();
        }
        return DBConnection.instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connectToMySQL(String dbName, String user, String password) throws SQLException, ClassNotFoundException {
        if(this.connection != null) {
            this.connection.close();
        }
        Class.forName("com.mysql.jdbc.Driver");
        this.provider = DBProvider.MYSQL;
        this.connection = DriverManager.getConnection(String.format(MYSQL_CONNECTION, dbName), user, password);
    }

    public void connectToSQLServer(String dbName, String user, String password) throws SQLException {
        if(this.connection != null) {
            this.connection.close();
        }
        this.provider = DBProvider.SQLSERVER;
        this.connection = DriverManager.getConnection(String.format(SQLSERVER_CONNECTION, dbName, user, password));
    }
}
