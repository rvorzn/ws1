package ru.rymyantsev.db.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


public class SQLite implements BaseConnetcor  {
    private static SQLite ourInstance = new SQLite();

    //private static final String URL = "jdbc:sqlite:../webapps/ws-translate/BaseTranslate.db"; //для сервера
    private static final String URL = "jdbc:sqlite:src/main/webapp/META-INF/BaseTranslate.db";
    private static final String DRIVER_TO_BASE = "org.sqlite.JDBC";



    private SQLite() {
        try {
            Class.forName(DRIVER_TO_BASE);
        } catch (ClassNotFoundException e) {
            Logger.getGlobal().severe(e.toString());
        }
    }

    // get Instance
    public static SQLite getInstance() {
        return ourInstance;
    }

    // get Connection
    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    
}
