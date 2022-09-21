package dvdlibrary.ui;

import io.UserIO;

public class ViewMenuMain extends ViewImpl {

	public ViewMenuMain(UserIO io) {
		super(io);
	}

	@Override
	public String getTitle() {
		return "Main Menu";
	}

	@Override
	public void render() {

		super.render();

		io.print("\n1: Add a DVD\n"
		+ "2: Search for a DVD\n"
		+ "3: Display all DVDs\n\n"

		+ "0: Exit\n\n"

		+ "Hint: Search for a DVD entry to edit or remove it.");
	}
}
