package c299.guessthenumber.dao;

import java.util.List;

public interface DAO<T> {
    T getById(int id);
    List<T> getAll();
    T add(T object);
    boolean update(T object);
    boolean removeById(int id);
}
