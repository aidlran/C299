package c299.superherosightings.dto;

import lombok.Data;

@Data
public class Organisation implements DTO {
    private int id;
	private String name;
	private String description;
	private Integer contactDetailsId;
}