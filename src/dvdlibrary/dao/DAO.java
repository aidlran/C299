package dvdlibrary.dao;

import dvdlibrary.dto.DVD;

public interface DAO {
    public void init() throws DAOException;
    public void close() throws DAOException;
    public void addDVD(DVD dvd);
    public void addDVD(String movieName);
    public DVD[] getAllDVDs();
}
