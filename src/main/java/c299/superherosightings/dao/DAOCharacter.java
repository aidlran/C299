package c299.superherosightings.dao;

import java.util.List;

import c299.superherosightings.dto.SuperCharacter;

public interface DAOCharacter extends DAO<SuperCharacter> {
	List<SuperCharacter> getByOrganisation(int organisationID);
}
