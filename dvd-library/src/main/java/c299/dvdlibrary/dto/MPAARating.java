package c299.dvdlibrary.dto;

public enum MPAARating {

	G("G"),
	PG("PG"),
	PG_13("PG-13"),
	R("R"),
	NC_17("NC-17");

	private String value;

	MPAARating(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static MPAARating parse(String mpaaRating) throws MPAARatingParseException {
		return switch (mpaaRating) {
			case "G" -> G;
			case "PG" -> PG;
			case "PG-13" -> PG_13;
			case "R" -> R;
			case "NC-17" -> NC_17;
			default -> throw new MPAARatingParseException(mpaaRating);
		};
	}
}
