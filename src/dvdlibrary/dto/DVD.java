package dvdlibrary.dto;

import java.sql.Date;

public class DVD {

	private String movieTitle;
	private Date releaseDate;
	private String studioName;
	private String directorName;
	private MPAARating mpaaRating;
	private char userRating;
	private String userNote;

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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

	public char getUserRating() {
		return userRating;
	}

	public void setUserRating(char userRating) {
		this.userRating = userRating;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}
}
