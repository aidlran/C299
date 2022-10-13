package c299.superherosightings.service;

import java.util.List;

import c299.superherosightings.dto.Location;
import c299.superherosightings.dto.Sighting;
import c299.superherosightings.dto.SuperCharacter;

public interface ServiceLayer {
    SuperCharacter getCharacter(int id);
    Location getLocation(int id);
    Sighting getSighting(int id);
    List<Sighting> getRecentSightings();
}
