package ru.rymyantsev.db.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface BaseConnetcor {
    Connection connect() throws SQLException;
}
