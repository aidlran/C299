package c299.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            sighting.setLocationId(resultSet.getInt("location_id"));
            sighting.setTimestamp(resultSet.getDate("timestamp"));
            return sighting;
        }
    }

    @Autowired
    DAOSightingImplJDBC(JdbcTemplate jdbcTemplate) {
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
    public Sighting add(Sighting object) {
		List<Integer> id = jdbcTemplate.query(
				"INSERT INTO " + getTableName() + " (description, timestamp, character_id, location_id) " +
				"VALUES (?, ?, ?, ?) " +
				"RETURNING id",
				new IdMapper(),
				object.getDescription(),
                object.getTimestamp(),
                object.getCharacterId(),
                object.getLocationId()
		);
		return (id.size() == 0 || (object = getById(id.get(0))) == null) ? null : object;
    }
}
