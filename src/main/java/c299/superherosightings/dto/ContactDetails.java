package c299.superherosightings.dto;

import lombok.Data;

@Data
public class ContactDetails implements DTO {
    private int id;
    private String phoneNumber;
    private String emailAddress;
    private Integer locationId;
    private String note;
}
