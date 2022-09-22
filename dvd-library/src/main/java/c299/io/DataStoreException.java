package c299.io;

/**
 * An Exception raised by the data store.
 */
public class DataStoreException extends Exception {

    public DataStoreException(String error) {
        super(error);
    }

    public DataStoreException(String error, Throwable cause) {
        super(error, cause);
    }
}
