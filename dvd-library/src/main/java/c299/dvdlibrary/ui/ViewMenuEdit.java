package c299.dvdlibrary.ui;

import c299.dvdlibrary.dto.DVD;
import c299.io.UserIO;

public class ViewMenuEdit extends ViewImpl {

	DVD dvd;

	public ViewMenuEdit(UserIO io, DVD dvd) {
		super(io);
		this.dvd = dvd;
	}

	@Override
	public String getTitle() {
		return dvd.getMovieTitle();
	}

	@Override
	public void render() {

		super.render();

		String stringStore;

		io.print("Title: " + dvd.getMovieTitle());
		if ((stringStore = dvd.getReleaseDateString()) != null) io.print("Release date: " + stringStore);
		if (dvd.getStudioName() != null) io.print("Studio: " + dvd.getStudioName());
		if (dvd.getDirectorName() != null) io.print("Director: " + dvd.getStudioName());
		if (dvd.getMpaaRating() != null) io.print("MPAA rating: " + dvd.getMpaaRating().toString());
		if (dvd.getUserRating() > 0) io.print("Your rating: " + dvd.getUserRating() + "/10");
		if (dvd.getUserNote() != null) io.print("Notes: " + dvd.getUserNote());

		io.print("\n1: Change the DVD name");
		io.print("2: Change the release date");
		io.print("3: Change the studio name");
		io.print("4: Change the director's name");
		io.print("5: Change the MPAA rating");
		io.print("6: Change your user rating");
		io.print("7: Change notes");

		io.print("\n8: DELETE ENTRY");

		io.print("\n0: Back");
	}
}
