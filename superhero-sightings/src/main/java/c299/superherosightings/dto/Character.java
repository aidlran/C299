package c299.superherosightings.dto;

import lombok.Data;

@Data
public abstract class Character {

    public static enum Type {
        HERO,
        VILLAIN
    };

    private String name;
    private String description;
    private String superpower;
    private ContactDetails contactDetails;

    public abstract Type getType();
}
