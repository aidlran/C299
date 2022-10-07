package c299.superherosightings.dto;

import lombok.Data;

@Data
public class Organisation {
    private int id;
	private String name;
	private String description;
	private int contactDetailsId;
}