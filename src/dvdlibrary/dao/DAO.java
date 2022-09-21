package dvdlibrary.dao;

import java.util.Map;

import dvdlibrary.dto.DVD;

public interface DAO {
    public void init() throws DAOException;
    public void close() throws DAOException;
    public int addDVD(DVD dvd);
    public int addDVD(String movieName);
    public DVD[] getAllDVDs();
    public Map<Integer, String> searchDVDs(String query);
}
