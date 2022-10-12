package c299.superherosightings.service;

import java.util.List;

import c299.superherosightings.dto.Sighting;

public interface ServiceLayer {
    List<Sighting> getRecentSightings();
}
