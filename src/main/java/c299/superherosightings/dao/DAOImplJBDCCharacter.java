package c299.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import c299.superherosightings.dto.SuperCharacter;

@Repository
public class DAOImplJBDCCharacter extends DAOImplJBDC<SuperCharacter> {

	BiMap<Integer, String> characterTypes = HashBiMap.create();

	private static final class CharacterMapper implements RowMapper<SuperCharacter> {
	
		BiMap<Integer, String> characterTypes;

		public CharacterMapper(BiMap<Integer, String> characterTypes) {
			super();
			this.characterTypes = characterTypes;
		}

		@Override
		public SuperCharacter mapRow(ResultSet resultSet, int index) throws SQLException {
			SuperCharacter character = new SuperCharacter();
			character.setId(resultSet.getInt("id"));
			character.setType(this.characterTypes.get(resultSet.getInt("type_id")));
			character.setName(resultSet.getString("name"));
			character.setDescription(resultSet.getString("description"));
			character.setSuperpower(resultSet.getString("superpower"));
			character.setContactDetailsId(resultSet.getInt("contact_details_id"));
			return character;
		}
	}

	@Autowired
	public DAOImplJBDCCharacter(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT * FROM character_type");
		while (result.next()) characterTypes.put(result.getInt("id"), result.getString("name"));
	}

	@Override
	protected String getTableName() {
		return "character";
	}

	@Override
	protected RowMapper<SuperCharacter> getRowMapper() {
		return new CharacterMapper(characterTypes);
	}

	@Override
	@Transactional
	public SuperCharacter add(SuperCharacter character) {
		List<Integer> id = jdbcTemplate.query(
				"INSERT INTO character (type_id, contact_details_id, name, description, superpower) " +
				"VALUES (?, ?, ?, ?, ?) " +
				"RETURNING id",
				new IdMapper(),
				characterTypes.inverse().get(character.getType()),
				character.getContactDetailsId(),
				character.getName(),
				character.getDescription(),
				character.getSuperpower()
		);
		return (id.size() == 0 || (character = getById(id.get(0))) == null) ? null : character;
	}
}
