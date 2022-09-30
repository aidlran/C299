package c299.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataStoreFile {

    private File file;

    public DataStoreFile(String fileName) {
        this.file = new File(fileName);
    }

    /**
	 * Creates the file if it does not exist.
     * Read & Write already will take care of this.
	 * @return TRUE if a fresh file had to be created, otherise FALSE.
	 * @throws DataStoreException In the case of an IO error preventing file creation.
	 */
	public boolean init() throws DataStoreException {
		try {
			return file.createNewFile();
		} catch (IOException e) {
			throw new DataStoreException("Could not create file: " + file.getAbsolutePath(), e);
		}
	}

    /**
     * @return A Scanner object for reading the file.
     * @throws DataStoreException In the case of an IO error preventing file access.
     */
	public Scanner getReader() throws DataStoreException {
		try {
            init();
			return new Scanner(new BufferedReader(new FileReader(file)));
		} catch (FileNotFoundException e) {
			throw new DataStoreException("Could not access file: " + file.getAbsolutePath(), e);
		}
	}

    /**
     * @return A PrintWriter object for writing the file.
     * @throws DataStoreException In the case of an IO error preventing file write.
     */
    public PrintWriter getWriter() throws DataStoreException {
		try {
			return new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			throw new DataStoreException("Could not write file: " + file.getAbsolutePath(), e);
		}
    }
}
