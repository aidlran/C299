package c299.vendingmachine.dao;

import c299.vendingmachine.dto.Item;

public interface DAO {
    public void init() throws DAOException;
    public void close() throws DAOException;
    public Item[] getAllItems();
}
