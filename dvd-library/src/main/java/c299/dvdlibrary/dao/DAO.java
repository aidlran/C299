package c299.dvdlibrary.dao;

import java.util.Map;

import c299.dvdlibrary.dto.DVD;

public interface DAO {
    public void init() throws DAOException;
    public void close() throws DAOException;
    public int addDVD(DVD dvd);
    public int addDVD(String movieName);
    public void removeDVD(int dvdIndex);
    public void updateDVD(int dvdIndex, DVD dvd);
    public DVD getDVD(int dvdIndex);
    public DVD[] getAllDVDs();
    public Map<Integer, String> searchDVDs(String query);
}
