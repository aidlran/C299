package c299.superherosightings.dto;

import lombok.Data;

/**
 * Called SuperCharacter in to prevent conflicts
 * with the Java primitive wrapper `Character`.
 * This data transfer class repesents a Hero or a Villain.
 */
@Data
public class SuperCharacter implements DTO {
    private int id;
    private String name;
    private String description;
    private String superpower;
    private Integer contactDetailsId;
    private String type;
}
