package c299.superherosightings.dao;

/**
 * An Exception raised by the DAO.
 */
public class DAOException extends Exception {

	public DAOException(String error) {
		super(error);
	}

	public DAOException(String error, Throwable cause) {
		super(error, cause);
	}
}
