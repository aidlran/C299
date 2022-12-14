package c299.superherosightings.dto;

import lombok.Data;

@Data
public class ContactDetails {
    private int id;
    private String phoneNumber;
    private String emailAddress;
    private Location address;
    private String note;
}
