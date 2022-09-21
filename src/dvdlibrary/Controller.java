package dvdlibrary;

import java.util.Map;

import dvdlibrary.dao.DAO;
import dvdlibrary.dao.DAOException;
import dvdlibrary.ui.ViewImpl;
import dvdlibrary.ui.ViewListDVDs;
import dvdlibrary.ui.ViewMenuMain;
import dvdlibrary.ui.ViewSearchResults;
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
		do {

			view.render();

			try {
				switch (view.readInt("\nEnter your choice (0-3)", 0, 3)) {
					case 1:
						runEditMenu(dao.addDVD(view.readString("Enter the DVD name")));
						break;
					case 2:
						runSearch();
						break;
					case 3:
						new ViewListDVDs(userIO, dao.getAllDVDs()).render();
						break;
					case 0:
						dao.close();
						System.exit(0);
						return;
					default:
						view.displayErrorMessage("Unknown command.");
				}
			} catch (DAOException e) {
				view.displayErrorMessage(e.getMessage());
			}
		} while (true);
	}

	private void runSearch() {
		Map<Integer, String> results = dao.searchDVDs(view.readString("Enter search query"));
		if (results.size() < 1) view.print("No results found.");
		else {
			new ViewSearchResults(userIO, results).render();
			int userChoice;
			do {
				userChoice = view.readInt("\nChoose a DVD's ID to manage it");
				if (results.containsKey(userChoice)) {
					runEditMenu(userChoice);
					return;
				}
				else view.print("That ID is not in the results.");
			} while (true);
		}
	}

	private void runEditMenu(int dvdIndex) {
		System.out.println("==EDIT==");
	}
}
