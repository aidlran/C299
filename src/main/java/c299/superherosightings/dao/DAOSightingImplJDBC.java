package c299.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import c299.superherosightings.dto.Sighting;

@Repository
public class DAOSightingImplJDBC extends DAOImplJBDC<Sighting> implements DAOSighting {

	private static final class SightingMapper implements RowMapper<Sighting> {
		@Override
		public Sighting mapRow(ResultSet resultSet, int index) throws SQLException {
			Sighting sighting = new Sighting();
			sighting.setId(resultSet.getInt("id"));
			sighting.setDescription(resultSet.getString("description"));
			sighting.setCharacterId(resultSet.getInt("character_id"));
			if (resultSet.wasNull()) sighting.setCharacterId(null);
			sighting.setLocationId(resultSet.getInt("location_id"));
			if (resultSet.wasNull()) sighting.setLocationId(null);
			sighting.setTimestamp(resultSet.getDate("timestamp"));
			return sighting;
		}
	}

	@Autowired
	public DAOSightingImplJDBC(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	protected String getTableName() {
		return "sighting";
	}

	@Override
	protected RowMapper<Sighting> getRowMapper() {
		return new SightingMapper();
	}

	@Override
	public Sighting add(Sighting sighting) {
		List<Integer> id = jdbcTemplate.query(
			"INSERT INTO " + getTableName() + " (description, timestamp, character_id, location_id) " +
			"VALUES (?, ?, ?, ?) " +
			"RETURNING id",
			new IdMapper(),
			sighting.getDescription(),
			sighting.getTimestamp(),
			sighting.getCharacterId(),
			sighting.getLocationId()
		);
		return (id.size() == 0 || (sighting = getById(id.get(0))) == null) ? null : sighting;
	}

	@Override
	public Sighting update(Sighting sighting) {
		return jdbcTemplate.update(
			"UPDATE " + getTableName() + " " +
			"SET description = ?, timestamp = ?, character_id = ?, location_id = ? " +
			"WHERE id = ?",
			sighting.getDescription(),
			sighting.getTimestamp(),
			sighting.getCharacterId(),
			sighting.getLocationId(),
			sighting.getId()
		) == 0 ? null : sighting;
	}

	@Override
	public List<Sighting> getByCharacterID(int characterID) {
		return jdbcTemplate.query(
			"SELECT * FROM sighting WHERE character_id = ?",
			getRowMapper(),
			characterID
		);
	}

	@Override
	public List<Sighting> getByLocationID(int locationID) {
		return jdbcTemplate.query(
			"SELECT * FROM sighting WHERE location_id = ?",
			getRowMapper(),
			locationID
		);
	}

	@Override
	public List<Sighting> getByCharacterAndLocationIDs(int characterID, int locationID) {
		return jdbcTemplate.query(
			"SELECT * FROM sighting WHERE character_id = ? AND location_id = ?",
			getRowMapper(),
			characterID,
			locationID
		);
	}

	@Override
	public List<Sighting> getByDate(Date date) {
		return jdbcTemplate.query(
			"SELECT * FROM sighting WHERE timestamp::DATE = ?",
			getRowMapper(),
			date
		);
	}

	@Override
	public List<Sighting> getRecent(int limit) {
		return jdbcTemplate.query(
			"SELECT * FROM sighting ORDER BY timestamp DESC LIMIT ?",
			getRowMapper(),
			limit
		);
	}
}
