package c299.superherosightings.dto;

import lombok.Data;

@Data
public class Location implements DTO {

    private int id;
    private String name;
    private String description;
    private String street;
    private String postalCode;
    // private double[] coordinates;
    private double longitude, latitude;

    // public void setCoordinates(double longitude, double latitude) {
    //     double[] coordinates = {
    //         longitude,
    //         latitude
    //     };
    //     this.coordinates = coordinates;
    // }
}
