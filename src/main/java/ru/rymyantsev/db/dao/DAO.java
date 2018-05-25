package ru.rymyantsev.db.dao;

import java.util.List;

public interface DAO<Entity, Key> {

    //adding
    boolean add(Entity model);

    //reading
    List<Entity> read(Key key);
    List<Entity> readAll();

    //updating
    boolean update(Entity model);

    //deleting
    boolean delete(Entity model);

}
