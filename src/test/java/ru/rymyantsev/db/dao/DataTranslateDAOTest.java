package ru.rymyantsev.db.dao;

import org.junit.Test;

import ru.rymyantsev.db.db.SQLite;
import ru.rymyantsev.db.model.DataTranslate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DataTranslateDAOTest {
    private final String SOURCE_TEXT = "слово";
    private final String TRANSLATE_TEXT = "word";
    private final String IP = "127.0.0.1";

    private final DataTranslate model = new DataTranslate(SOURCE_TEXT, TRANSLATE_TEXT, new Date().toString(), IP);



    @Test
    public void addReadDeleteTest() {
        boolean result = false;
        boolean resultRead = false;
        boolean resultDelete= false;


        List<DataTranslate> resultList = new ArrayList<>();

        try (Connection connect = SQLite.getInstance().connect()){
            //добавляем данные
            DataTranslateDAO dataTranslateDAO = new DataTranslateDAO(connect);
            result = dataTranslateDAO.add(model);

            //читаем данные
            resultList = dataTranslateDAO.read("слово");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultList.size()>0) {resultRead = true;}

        //удаляем тестовые данные
        try (Connection connect = SQLite.getInstance().connect()){
            DataTranslateDAO dataTranslateDAO = new DataTranslateDAO(connect);
            resultDelete = dataTranslateDAO.delete(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(result && resultRead && resultDelete);

    }
}