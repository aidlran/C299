package c299.superherosightings.dao;

import java.util.List;

import c299.superherosightings.dto.Organisation;

public interface DAOOrganisation extends DAO<Organisation> {
	boolean addMember(int organisationID, int characterID);
	List<Organisation> getByCharacterID(int characterID);
}
