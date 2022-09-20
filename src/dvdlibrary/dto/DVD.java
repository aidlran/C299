package dvdlibrary.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DVD {

	private static final SimpleDateFormat RELEASE_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	private String movieTitle;
	private Date releaseDate;
	private String studioName;
	private String directorName;
	private MPAARating mpaaRating;
	private short userRating;
	private String userNote;

	public DVD(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getReleaseDateString() {
		return RELEASE_DATE_FORMATTER.format(releaseDate);
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setReleaseDate(String releaseDate) throws DVDDateParseException {
		try {
			this.releaseDate = RELEASE_DATE_FORMATTER.parse(releaseDate);
		} catch (ParseException e) {
			throw new DVDDateParseException("Error parsing an invalid DVD release date for: " + movieTitle, e);
		}
	}

	public String getStudioName() {
		return studioName;
	}

	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public MPAARating getMpaaRating() {
		return mpaaRating;
	}

	public void setMpaaRating(MPAARating mpaaRating) {
		this.mpaaRating = mpaaRating;
	}

	public void setMpaaRating(String mpaaRating) throws MPAARatingParseException {
		this.mpaaRating = MPAARating.parse(mpaaRating);
	}

	public short getUserRating() {
		return userRating;
	}

	public void setUserRating(short userRating) {
		this.userRating = userRating;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}
}
