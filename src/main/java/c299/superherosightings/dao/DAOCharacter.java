package c299.superherosightings.dao;

import java.util.List;

import c299.superherosightings.dto.SuperCharacter;

public interface DAOCharacter extends DAO<SuperCharacter> {
    public List<SuperCharacter> getByOrganisation(int organisationID);
}
