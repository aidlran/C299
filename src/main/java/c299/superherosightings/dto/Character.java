package c299.superherosightings.dto;

import lombok.Data;

@Data
public abstract class Character {

    public static enum Type {
        HERO,
        VILLAIN
    };

    private int id;
    private String name;
    private String description;
    private String superpower;
    private int contactDetailsId;

    public abstract Type getType();
}
