package dvdlibrary.ui;

import io.UserIO;

public class MenuMain extends ViewImplMenu {

	public MenuMain(UserIO io) {
		super(io);
	}

	public String getTitle() {
		return "Main Menu";
	}

	public String getContent() {
		return "1: Add a DVD\n"
		+ "2: Search for a DVD\n"
		+ "3: Display all DVDs\n\n"

		+ "Hint: Search for a DVD entry to edit or remove it.\n\n"

		+ "0: Exit";
	}
}
