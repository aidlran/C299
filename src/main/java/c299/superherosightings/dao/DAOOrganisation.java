package c299.superherosightings.dao;

import c299.superherosightings.dto.Organisation;

public interface DAOOrganisation extends DAO<Organisation> {
    public boolean addMember(int organisationID, int characterID);
}
