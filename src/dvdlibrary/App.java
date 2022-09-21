package dvdlibrary;

import dvdlibrary.dao.DAOException;
import dvdlibrary.dao.DAOImplFile;
import io.DataStoreFile;
import io.UserIO;
import io.UserIOImplConsole;

public class App {
	public static void main(String[] args) throws DAOException {
		UserIO userIO = new UserIOImplConsole();
		new Controller(
			new DAOImplFile(new DataStoreFile("dvd-library.txt")),
			userIO
		).run();
	}
}
