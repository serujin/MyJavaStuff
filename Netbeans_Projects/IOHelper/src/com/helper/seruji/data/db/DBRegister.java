package com.helper.seruji.data.db;

public class DBRegister {
    private DBDataTypes[] types;

    public DBRegister(DBDataTypes[] types) {
        this.types = types;
    }

    public DBDataTypes[] getTypes() {
        return this.types;
    }
}
