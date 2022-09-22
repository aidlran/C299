package c299.dvdlibrary;

import java.util.Map;

import c299.dvdlibrary.dao.DAO;
import c299.dvdlibrary.dao.DAOException;
import c299.dvdlibrary.dto.DVD;
import c299.dvdlibrary.dto.DVDDateParseException;
import c299.dvdlibrary.dto.MPAARatingParseException;
import c299.dvdlibrary.ui.ViewImpl;
import c299.dvdlibrary.ui.ViewListDVDs;
import c299.dvdlibrary.ui.ViewMenuEdit;
import c299.dvdlibrary.ui.ViewMenuMain;
import c299.dvdlibrary.ui.ViewSearchResults;
import c299.io.UserIO;

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
		DVD dvd = dao.getDVD(dvdIndex);
		view = new ViewMenuEdit(userIO, dvd);
		do {
			view.render();
			switch (view.readInt("\nEnter your choice (0-8)", 0, 8)) {
				case 1:
					dvd.setMovieTitle(userIO.readString("Enter the DVD name"));
					break;
				case 2:
					do { try {
						dvd.setReleaseDate(userIO.readString("Enter the release date (YYYY-MM-DD)"));
						break;
					} catch (DVDDateParseException e) {
						view.displayErrorMessage("Invalid date format.");
					} } while (true);
					break;
				case 3:
					dvd.setStudioName(userIO.readString("Enter the studio name"));
					break;
				case 4:
					dvd.setDirectorName(userIO.readString("Enter the director's name"));
					break;
				case 5:
					do { try {
						dvd.setMpaaRating(userIO.readString("Enter the MPAA rating (G, PG, PG-13, R, NC-17)").toUpperCase());
						break;
					} catch (MPAARatingParseException e) {
						view.displayErrorMessage("Unrecognised MPAA rating.");
					} } while (true);
					break;
				case 6:
					dvd.setUserRating((short)userIO.readInt("Enter your rating (1-10). Type 0 to remove rating.", 0, 10));
					break;
				case 7:
					dvd.setUserNote(userIO.readString("Enter your notes"));
					break;
				case 8:
					if (userIO.readString("Type 'DELETE' to confirm deletion").equals("DELETE")) {
						dao.removeDVD(dvdIndex);
						view = new ViewMenuMain(userIO);
						return;
					}
					userIO.print("Operation cancelled.");
					break;
				case 0:
					dao.updateDVD(dvdIndex, dvd);
					view = new ViewMenuMain(userIO);
					return;
				default:
					view.displayErrorMessage("Unknown command.");
			}
		} while (true);
	}
}
