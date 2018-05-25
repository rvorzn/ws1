package ru.rymyantsev.db.db;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static ru.rymyantsev.db.db.SQLite.getInstance;

public class SQLiteTest {

    BaseConnetcor instance;

    @Before
    public void getInsatnce(){
        instance = SQLite.getInstance();
    }

    @Test
    public void whenInsanseNotNullThenTrue() {
        boolean result = instance != null;
        assertTrue(result);
    }


}