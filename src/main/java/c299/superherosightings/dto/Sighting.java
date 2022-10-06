package c299.superherosightings.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Sighting {
    private int id;
	private int characterId;
	private Date timestamp;
	private int locationId;
	private String description;
}
