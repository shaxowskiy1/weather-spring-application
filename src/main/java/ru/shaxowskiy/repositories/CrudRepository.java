package ru.shaxowskiy.repositories;

import java.util.List;

public interface CrudRepository <T, ID> {

    T findById(ID id);

    List<T> findAll();

    void save(T entity);

    void update(T entity, ID id);

    void delete(ID id);
}
