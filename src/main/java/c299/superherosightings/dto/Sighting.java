package c299.superherosightings.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Sighting implements DTO {
    private int id;
	private Integer characterId;
	private Date timestamp;
	private Integer locationId;
	private String description;
}
