package c299.superherosightings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c299.superherosightings.dao.DAOSighting;
import c299.superherosightings.dto.Sighting;

@Service
public class ServiceLayerImpl implements ServiceLayer {

    @Autowired
    private DAOSighting sightingDAO;

    public List<Sighting> getRecentSightings() {
        return sightingDAO.getRecent(10);
    }
}
