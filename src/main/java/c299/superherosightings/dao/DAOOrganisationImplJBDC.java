package c299.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import c299.superherosightings.dto.Organisation;

@Repository
public class DAOOrganisationImplJBDC extends DAOImplJBDC<Organisation> implements DAOOrganisation {

	private static final class OrganisationMapper implements RowMapper<Organisation> {
		@Override
		public Organisation mapRow(ResultSet resultSet, int index) throws SQLException {
			Organisation organisation = new Organisation();
			organisation.setId(resultSet.getInt("id"));
			organisation.setName(resultSet.getString("name"));
			organisation.setDescription(resultSet.getString("description"));
			organisation.setContactDetailsId(resultSet.getInt("contact_details_id"));
			if (resultSet.wasNull()) organisation.setContactDetailsId(null);
			return organisation;
		}
	}

	@Autowired
	public DAOOrganisationImplJBDC(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
	}

    @Override
    protected String getTableName() {
        return "organisation";
    }

    @Override
    protected RowMapper<Organisation> getRowMapper() {
        return new OrganisationMapper();
    }
 
    @Override
    public Organisation add(Organisation organisation) {
		List<Integer> id = jdbcTemplate.query(
			"INSERT INTO " + getTableName() + " (contact_details_id, name, description) " +
			"VALUES (?, ?, ?) " +
			"RETURNING id",
			new IdMapper(),
			organisation.getContactDetailsId(),
			organisation.getName(),
			organisation.getDescription()
		);
		return (id.size() == 0 || (organisation = getById(id.get(0))) == null) ? null : organisation;
    }

	@Override
	public Organisation update(Organisation organisation) {
		return jdbcTemplate.update(
			"UPDATE " + getTableName() + " " +
			"SET contact_details_id = ?, name = ?, description = ? " +
			"WHERE id = ?",
			organisation.getContactDetailsId(),
			organisation.getName(),
			organisation.getDescription(),
			organisation.getId()
		) == 0 ? null : organisation;
	}

	@Override
	public boolean addMember(int organisationID, int characterID) {
		return jdbcTemplate.update(
			"INSERT INTO organisation_member (organisation_id, character_id) " +
			"VALUES (?, ?)",
			organisationID,
			characterID
		) > 0;
	}

	@Override
    public List<Organisation> getByCharacterID(int characterID) {
		return jdbcTemplate.query(
			"SELECT DISTINCT o.* FROM organisation o " +
			"JOIN organisation_member om ON o.id = om.organisation_id " +
			"WHERE om.character_id = ?",
			getRowMapper(),
			characterID
		);
	}
}
