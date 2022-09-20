package dvdlibrary;

import dvdlibrary.dao.DAOImplFile;
import dvdlibrary.ui.MenuMain;
import io.DataStoreFile;
import io.UserIOImplConsole;

public class App {

	private static final Controller CONTROLLER = new Controller(
		new DAOImplFile(new DataStoreFile("dvd-library.txt")),
		new MenuMain(new UserIOImplConsole())
	);

	public static void main(String[] args) {
		CONTROLLER.run();
	}
}
