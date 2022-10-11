package c299.superherosightings.dao;

import java.util.List;

public interface DAO<T> {
	T getById(int id);
	List<T> getAll();
	T add(T object);
	T update(T object);
	boolean removeById(int id);
}
