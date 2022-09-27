package c299.superherosightings.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import c299.superherosightings.dto.Character;

public class SuperheroDAOImplDB implements SuperheroDAO {

	@Autowired
	JdbcTemplate db;

    @Transactional
	public boolean addCharacter(Character character) {
		
		return db.update("INSERT INTO character(name, description, superpower, type_id, contact_details_id)"
				+ "VALUES (?,?,?,(SELECT id FROM character_type WHERE name = ?),?);",
				character.getName(),
				character.getDescription(),
				character.getSuperpower(),
				character.getType().name(),
				character.getContactDetails() == null ? null : character.getContactDetails().getId()) > 0;
	}
}
