package dvdlibrary.dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public DAOImplFile(DataStoreFile dataStore) throws DAOException {
		this.persist = dataStore;
		init();
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

	private boolean isFieldNull(String field) {
		return switch (field.length()) {
			case 0 -> true;
			case 1 -> field.charAt(0) == ' ';
			default -> false;
		};
	}

	private DVD unmarshall(String marshalledObject) {

		String[] tokens = marshalledObject.split(DELIMITER);

		DVD object = new DVD(tokens[0]);

		// Strings are no problem
		if (!isFieldNull(tokens[2])) object.setStudioName(tokens[2]);
		if (!isFieldNull(tokens[3])) object.setDirectorName(tokens[3]);
		if (!isFieldNull(tokens[6])) object.setUserNote(tokens[6]);

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
			object.getReleaseDateString() == null ? null : object.getReleaseDateString(),
			object.getStudioName(),
			object.getDirectorName(),
			object.getMpaaRating() == null ? null : object.getMpaaRating().toString(),
			Short.toString(object.getUserRating()),
			object.getUserNote()
		};

		String marshalledObject = "";

		for (int i = 0; i < tokens.length; i++) {
			marshalledObject += (tokens[i] == null ? " " : tokens[i]);
			if (i < tokens.length - 1) marshalledObject += DELIMITER;
		}

		return marshalledObject;
	}

	@Override
	public void init() throws DAOException {
		try {
			read();
		} catch (DataStoreException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	@Override
	public void close() throws DAOException {
		try {
			write();
		} catch (DataStoreException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	@Override
	public int addDVD(DVD dvd) {
		DVD_LIBRARY.add(dvd);
		return DVD_LIBRARY.size() - 1;
	}

	@Override
	public int addDVD(String movieName) {
		return addDVD(new DVD(movieName));
	}

	@Override
	public DVD[] getAllDVDs() {

		int dvdCount = DVD_LIBRARY.size();
		DVD[] allDVDs = new DVD[dvdCount];

		// DVD library should only be changed through DAO
		// So have to be careful not to return any reference to existing DVD objects
		// Therefore we clone each DVD into a new object
		for (int i = 0; i < dvdCount; i++)
			allDVDs[i] = new DVD(DVD_LIBRARY.get(i));

		return allDVDs;
	}

	public Map<Integer, String> searchDVDs(String query) {

		Map<Integer, String> results = new HashMap<>();

		DVD currentDVD = null;
		for (int i = 0; i < DVD_LIBRARY.size(); i++) {
			currentDVD = DVD_LIBRARY.get(i);
			if (currentDVD.getMovieTitle().toLowerCase().contains(query.toLowerCase()))
				results.put(i, currentDVD.getMovieTitle());
		}

		return results;
	}

	@Override
	public void removeDVD(int dvdIndex) {
		DVD_LIBRARY.remove(dvdIndex);
	}

	@Override
	public void updateDVD(int dvdIndex, DVD dvd) {
		DVD_LIBRARY.set(dvdIndex, dvd);
	}

	@Override
	public DVD getDVD(int dvdIndex) {
		return new DVD(DVD_LIBRARY.get(dvdIndex));
	}
}
