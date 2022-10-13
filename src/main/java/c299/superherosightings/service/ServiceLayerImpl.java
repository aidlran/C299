package c299.superherosightings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c299.superherosightings.dao.DAOCharacter;
import c299.superherosightings.dao.DAOLocation;
import c299.superherosightings.dao.DAOSighting;
import c299.superherosightings.dto.Location;
import c299.superherosightings.dto.Sighting;
import c299.superherosightings.dto.SuperCharacter;

@Service
public class ServiceLayerImpl implements ServiceLayer {

    @Autowired private DAOCharacter characterDAO;
    @Autowired private DAOLocation locationDAO;
    @Autowired private DAOSighting sightingDAO;

    public SuperCharacter getCharacter(int id) {
        return characterDAO.getById(id);
    }

    public Location getLocation(int id) {
        return locationDAO.getById(id);
    }

    public Sighting getSighting(int id) {
        return sightingDAO.getById(id);
    }

    public List<Sighting> getRecentSightings() {
        return sightingDAO.getRecent(10);
    }
}
