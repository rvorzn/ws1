package ru.rymyantsev.bl.treads;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import ru.rymyantsev.db.model.DataTranslate;
import ru.rymyantsev.db.dao.DataTranslateDAO;
import ru.rymyantsev.db.db.BaseConnetcor;
import ru.rymyantsev.db.db.SQLite;


import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public final class WriteTranslateWordToBase implements Runnable {
    private final Logger logger = Logger.getLogger(WriteTranslateWordToBase.class);

    private final String TABLE = "translateSession";
    private final DataTranslate dataTranslate;

    @Override
    public void run() {

        BaseConnetcor instanceDB = SQLite.getInstance();

        synchronized (instanceDB){
            try (Connection connect = instanceDB.connect()) {

                if (!DataTranslateDAO.isTable(connect, TABLE)){ DataTranslateDAO.createTableTranlsatetion(connect); }

                DataTranslateDAO dataTranslateDAO = new DataTranslateDAO(connect);
                dataTranslateDAO.add(dataTranslate);

            } catch (SQLException e) {
                logger.warn("Ошибка при записи в базу", e);
            }
        }
    }
}
