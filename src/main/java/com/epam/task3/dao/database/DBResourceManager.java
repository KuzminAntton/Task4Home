package com.epam.task3.dao.database;


import java.util.Locale;
import java.util.ResourceBundle;

public class DBResourceManager {
    private final static  DBResourceManager instance  = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.ENGLISH);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
