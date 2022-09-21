package dvdlibrary;

import dvdlibrary.dao.DAO;
import dvdlibrary.ui.ViewImpl;
import dvdlibrary.ui.ViewListDVDs;
import dvdlibrary.ui.ViewMenuMain;
import io.UserIO;

public class Controller {

	private DAO dao;
	private UserIO userIO;
	private ViewImpl view;

	public Controller(DAO dao, UserIO userIO, ViewImpl view) {
		this.dao = dao;
		this.userIO = userIO;
		this.view = view;
	}

	public Controller(DAO dao, UserIO userIO) {
		this(dao, userIO, new ViewMenuMain(userIO));
	}

	public void run() {
		try {
			do {
				view.render();
				switch (view.readInt("\nEnter your choice (0-3)", 0, 3)) {
					case 1:
						dao.addDVD(view.readString("Enter the DVD name"));
						break;
					case 3:
						new ViewListDVDs(userIO, dao.getAllDVDs()).render();
						break;
					case 0:
						dao.close();
						return;
					default:
						view.displayErrorMessage("Unknown command.");
				}
			} while (true);
		} catch (Exception e) {
			view.displayErrorMessage(e.getMessage());
		}
	}
}
