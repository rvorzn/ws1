package ru.rymyantsev.db.dao;

import org.apache.log4j.Logger;
import ru.rymyantsev.db.model.DataTranslate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DataTranslateDAO implements DAO<DataTranslate, String> {
    private final Logger logger = org.apache.log4j.Logger.getLogger(DataTranslateDAO.class);

    private final Connection connection;

    public DataTranslateDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(DataTranslate model) {
        boolean result = true;

        try (PreparedStatement statement = connection.prepareStatement(SQL.INSERT_INTO_DATA_TRANSLATE.QUERY)) {

            createStatementAllParams(statement, model);

            statement.execute();
        } catch (SQLException e) {
            logger.warn("Ошибка при записи в БД add(DataTranslate model) ", e);
            result = false;
        }

        return result;
    }

    @Override
    public List<DataTranslate> read(String key) {
        List<DataTranslate> listData = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL.SELECT_DATA_TRANSLATE.QUERY)) {
            statement.setString(1, key);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DataTranslate model = new DataTranslate();

                model.setSourceWorld(rs.getString("sourceWord"));
                model.setTranslateWorld(rs.getString("translatedWord"));
                model.setDate(rs.getString("date"));
                model.setIp(rs.getString("ipClient"));

                listData.add(model);
            }

        } catch (SQLException e) {
            logger.warn("Ошибка при чтение из БД read(String key) ", e);
        }

        return listData;
    }

    @Override
    public List<DataTranslate> readAll() {
        return null;
    }

    @Override
    public boolean update(DataTranslate model) {
        return false;
    }

    @Override
    public boolean delete(DataTranslate model) {
        boolean result = true;

        try (PreparedStatement statement = connection.prepareStatement(SQL.DELETE_DATA_TRANSLATE.QUERY)) {

            createStatementAllParams(statement, model);
            statement.execute();

        } catch (SQLException e) {
            logger.warn("Ошибка при удалени из БД delete(DataTranslate model) ", e);
            result = false;

        }

        return result;
    }

    // private methods -----------------------------------------------------------------
    private void createStatementAllParams(PreparedStatement statement, DataTranslate model) {
        try {
            statement.setString(1, model.getSourceWorld());
            statement.setString(2, model.getTranslateWorld());
            statement.setString(3, model.getDate());
            statement.setString(4, model.getIp());
        } catch (SQLException e) {
            logger.warn("Ошибка при создание PreparedStatement createStatementAllParams(PreparedStatement statement, DataTranslate model) ", e);
        }
    }


    // utils static methods --------------------------------------------------------------

    public static boolean isTable(Connection connection,String tableName) throws SQLException {
        boolean exist = false;

        try (Statement statement = connection.createStatement() ) {

            ResultSet rs = statement.executeQuery(SQL.SELECT_TABLE_FROM_DB.QUERY);

            while (rs.next()) {
                String currentTable =  rs.getString("tbl_name");
                if (tableName.equals(currentTable)){
                    exist = true; break;
                }
            }
        }

        return exist;
    }

    public static void createTableTranlsatetion(Connection connect) throws SQLException {
        try (Statement statement = connect.createStatement()){
            statement.executeUpdate(SQL.CREATE_TABLE_TRANSLATE_SESSION.QUERY);
        }
    }

    // ---------------------------------------------- sql query --------------------------------------------------------

    private enum SQL {
        CREATE_TABLE_TRANSLATE_SESSION("CREATE TABLE translateSession " +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sourceWord VARCHAR(256), " +
                "translatedWord VARCHAR(256), "+
                "ipClient VARCHAR(128), "+
                "date VARCHAR(100) " +
                ")"
        ),

        SELECT_DATA_TRANSLATE("SELECT * FROM translateSession WHERE sourceWord = ?"),

        DELETE_DATA_TRANSLATE("DELETE FROM translateSession WHERE sourceWord = ? AND translatedWord = ? AND date = ? AND ipClient = ?"),

        SELECT_TABLE_FROM_DB("SELECT tbl_name FROM sqlite_master"),

        INSERT_INTO_DATA_TRANSLATE("INSERT INTO translateSession(sourceWord, translatedWord, date, ipClient ) values(?, ?, ?, ?)");


        final String QUERY;

        SQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}


