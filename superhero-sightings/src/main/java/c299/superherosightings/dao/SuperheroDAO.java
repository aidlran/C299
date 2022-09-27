package c299.superherosightings.dao;

import c299.superherosightings.dto.Character;

public interface SuperheroDAO {
    public boolean addCharacter(Character character) throws DAOException;
}
