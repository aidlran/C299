package c299.superherosightings.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Sighting {
	private Character character;
	private Date timestamp;
	private Location location;
	private String description;
}
