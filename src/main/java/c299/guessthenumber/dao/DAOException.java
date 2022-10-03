package c299.guessthenumber.dao;

public class DAOException extends Exception {

    public DAOException(String errorMessage) {
        super(errorMessage);
    }

    public DAOException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
