package c299.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import c299.superherosightings.dto.Location;

@Repository
public class DAOLocationImplJDBC extends DAOImplJBDC<Location> implements DAOLocation {

	private static final class LocationMapper implements RowMapper<Location> {
		@Override
		public Location mapRow(ResultSet resultSet, int index) throws SQLException {
			Location location = new Location();
			location.setId(resultSet.getInt("id"));
			location.setName(resultSet.getString("name"));
			location.setDescription(resultSet.getString("description"));
			location.setLongitude(resultSet.getDouble("longitude"));
			location.setLatitude(resultSet.getDouble("latitude"));
			location.setStreet(resultSet.getString("street"));
			location.setPostalCode(resultSet.getString("postal_code"));
			return location;
		}
	}

	@Autowired
	public DAOLocationImplJDBC(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	protected String getTableName() {
		return "location";
	}

	@Override
	protected RowMapper<Location> getRowMapper() {
		return new LocationMapper();
	}
	
	@Override
	public Location add(Location location) {
		List<Integer> id = jdbcTemplate.query(
			"INSERT INTO " + getTableName() + " (name, description, longitude, latitude, street, postal_code) " +
			"VALUES (?, ?, ?, ?, ?, ?) " +
			"RETURNING id",
			new IdMapper(),
			location.getName(),
			location.getDescription(),
			location.getLongitude(),
			location.getLatitude(),
			location.getStreet(),
			location.getPostalCode()
		);
		return (id.size() == 0 || (location = getById(id.get(0))) == null) ? null : location;
	}

	@Override
	public Location update(Location location) {
		return jdbcTemplate.update(
			"UPDATE " + getTableName() + " " +
			"SET name = ?, description = ?, longitude = ?, latitude = ?, street = ?, postal_code = ? " +
			"WHERE id = ?",
			location.getName(),
			location.getDescription(),
			location.getLongitude(),
			location.getLatitude(),
			location.getStreet(),
			location.getPostalCode(),
			location.getId()
		) == 0 ? null : location;
	}
}
