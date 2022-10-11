package c299.superherosightings.dao;

import java.util.Date;
import java.util.List;

import c299.superherosightings.dto.Sighting;

public interface DAOSighting extends DAO<Sighting> {
    List<Sighting> getByCharacterID(int characterID);
    List<Sighting> getByLocationID(int locationID);
    List<Sighting> getByCharacterAndLocationIDs(int characterID, int locationID);
    List<Sighting> getByDate(Date date);
}
