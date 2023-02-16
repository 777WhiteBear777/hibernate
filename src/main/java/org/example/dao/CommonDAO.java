package org.example.dao;

import java.util.List;

public interface CommonDAO<T> {

    List<T> getAll();

    Long addObj(T obj);

    T getById(Long id);

    void update(T obj);

    void delete(Long id);
}
