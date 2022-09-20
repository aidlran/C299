package dvdlibrary;

import dvdlibrary.dao.DAO;
import dvdlibrary.ui.ViewImplMenu;

public class Controller {

	private DAO dao;
	private ViewImplMenu view;

	public Controller(DAO dao, ViewImplMenu view) {
		this.dao = dao;
		this.view = view;
	}

	public void run() {
		try {
			do {
				switch (view.display()) {
					case 0:
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
