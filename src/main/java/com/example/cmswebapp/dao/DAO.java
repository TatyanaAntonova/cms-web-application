package com.example.cmswebapp.dao;

import java.util.List;

/**
 * Interface for working with DataBase(DB).
 */
public interface DAO<T> {
    /**
     * @param obj Object needs to be created in DB.
     *             Method adds the Object to DB.
     */
    void create(T obj);

    /**
     * @param id unique identifier of Object.
     * @param obj Object needs to be updated in DB.
     *             Method updates the Object.
     */
    void update(String id, T obj);

    /**
     * @param id unique identifier of Object.
     *           Method deletes an Object with id value.
     */
    void delete(String id);

    /**
     * @param id unique identifier of Object.
     * @return Object from DB with unique id value.
     */
    T get(String id);

    /**
     * @return Object List from DB.
     */
    List<T> getList();
}
