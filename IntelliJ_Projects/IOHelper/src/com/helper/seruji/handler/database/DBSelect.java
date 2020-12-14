package com.helper.seruji.handler.database;

import com.helper.seruji.data.db.DBDataTypes;
import com.helper.seruji.data.db.DBRegister;
import com.helper.seruji.handler.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSelect {
    protected static String select(String query, DBRegister register) throws SQLException {
        StringBuilder builder = new StringBuilder();
        ResultSet result = DBConnection.getInstance().getConnection().createStatement().executeQuery(query);
        while(result.next()) {
            builder.append(getStringFromDBRegister(result, register));
        }
        return builder.toString();
    }

    private static String getStringFromDBRegister(ResultSet result, DBRegister register) throws SQLException {
        StringBuilder builder = new StringBuilder();
        DBDataTypes[] types = register.getTypes();
        for(int i = 0; i < types.length; i++) {
            builder.append(getValueFromResultSet(result, types[i], (i + 1)) + "\n");
        }
        return builder.toString();
    }

    private static String getValueFromResultSet(ResultSet result, DBDataTypes type, int index) throws SQLException {
        switch (type) {
            case INT:
                return String.valueOf(result.getInt(index));
            case BOOLEAN:
                return String.valueOf(result.getBoolean(index));
            case LONG:
                return String.valueOf(result.getLong(index));
            case FLOAT:
                return String.valueOf(result.getFloat(index));
            case STRING:
                return result.getString(index);
            case DOUBLE:
                return String.valueOf(result.getDouble(index));
        }
        return null;
    }
}
