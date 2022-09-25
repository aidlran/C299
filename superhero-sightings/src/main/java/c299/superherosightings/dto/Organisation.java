package c299.superherosightings.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Organisation {

	private String name;
	private String description;
	private ContactDetails contactDetails;

	private List<Character> roster = new ArrayList<>();
}