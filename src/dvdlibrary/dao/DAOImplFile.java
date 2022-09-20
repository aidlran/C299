package dvdlibrary.dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dvdlibrary.dto.DVD;
import dvdlibrary.dto.DVDDateParseException;
import dvdlibrary.dto.MPAARatingParseException;
import io.DataStoreException;
import io.DataStoreFile;

public class DAOImplFile implements DAO {

	private static final String DELIMITER = "::";

	private final List<DVD> DVD_LIBRARY = new ArrayList<>();

	private DataStoreFile persist;

	public DAOImplFile(DataStoreFile dataStore) {
		this.persist = dataStore;
	}

	private void read() throws DataStoreException {

		Scanner in = persist.getReader();

		DVD_LIBRARY.clear();

		while (in.hasNextLine())
			DVD_LIBRARY.add(unmarshall(in.nextLine()));

		in.close();
	}

	private void write() throws DataStoreException {

		PrintWriter out = persist.getWriter();

		for (DVD object : DVD_LIBRARY)
			out.println(marshall(object));

		out.flush();
		out.close();
	}

	private DVD unmarshall(String marshalledObject) {

		String[] tokens = marshalledObject.split(DELIMITER);

		DVD object = new DVD(tokens[0]);

		// Strings are no problem
		object.setStudioName(tokens[2]);
		object.setDirectorName(tokens[3]);
		object.setUserNote(tokens[6]);

		// These must be parsed and can throw exceptions,
		// if this happens just leave the respective field null

		try {
			object.setUserRating(Short.parseShort(tokens[5]));
		} catch (NumberFormatException e) {}

		try {
			object.setReleaseDate(tokens[1]);
		} catch (DVDDateParseException e) {}

		try {
			object.setMpaaRating(tokens[4]);
		} catch (MPAARatingParseException e) {}

		return object;
	}

	private String marshall(DVD object) {

		String[] tokens = {
			object.getMovieTitle(),
			object.getReleaseDateString(),
			object.getStudioName(),
			object.getDirectorName(),
			object.getMpaaRating().toString(),
			Short.toString(object.getUserRating()),
			object.getUserNote()
		};

		String marshalledObject = "";

		boolean once = false;
		for (String token : tokens) {
			marshalledObject += token;
			if (once) marshalledObject += DELIMITER;
			else once = true;
		}

		return marshalledObject;
	}
}
