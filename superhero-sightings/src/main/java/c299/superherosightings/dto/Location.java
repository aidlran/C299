package c299.superherosightings.dto;

import lombok.Data;

@Data
public class Location {
    private String name;
    private String description;
    private String street;
    private String postalCode;
    private double longitude;
    private double latitude;
}
