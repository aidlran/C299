package c299.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import c299.superherosightings.dto.ContactDetails;

@Repository
public class DAOContactDetailsImplJDBC extends DAOImplJBDC<ContactDetails> implements DAOContactDetails {

	private static final class ContactDetailsMapper implements RowMapper<ContactDetails> {
		@Override
		public ContactDetails mapRow(ResultSet resultSet, int index) throws SQLException {
			ContactDetails contactDetails = new ContactDetails();
			contactDetails.setId(resultSet.getInt("id"));
			contactDetails.setLocationId(resultSet.getInt("location_id"));
			if (resultSet.wasNull()) contactDetails.setLocationId(null);
			contactDetails.setEmailAddress(resultSet.getString("email_address"));
			contactDetails.setPhoneNumber(resultSet.getString("phone_number"));
			contactDetails.setNote(resultSet.getString("note"));
			return contactDetails;
		}
	}

	@Autowired
	public DAOContactDetailsImplJDBC(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	protected String getTableName() {
		return "contact_details";
	}

	@Override
	protected RowMapper<ContactDetails> getRowMapper() {
		return new ContactDetailsMapper();
	}

	@Override
	public ContactDetails add(ContactDetails contactDetails) {
		List<Integer> id = jdbcTemplate.query(
			"INSERT INTO " + getTableName() + " (location_id, phone_number, email_address, note) " +
			"VALUES (?, ?, ?, ?) " +
			"RETURNING id",
			new IdMapper(),
			contactDetails.getLocationId(),
			contactDetails.getPhoneNumber(),
			contactDetails.getEmailAddress(),
			contactDetails.getNote()
		);
		return (id.size() == 0 || (contactDetails = getById(id.get(0))) == null) ? null : contactDetails;
	}

	@Override
	public ContactDetails update(ContactDetails contactDetails) {
		return jdbcTemplate.update(
			"UPDATE " + getTableName() + " " +
			"SET location_id = ?, phone_number = ?, email_address = ?, note = ? " +
			"WHERE id = ?",
			contactDetails.getLocationId() == null ? null : contactDetails.getLocationId(),
			contactDetails.getPhoneNumber(),
			contactDetails.getEmailAddress(),
			contactDetails.getNote(),
			contactDetails.getId()
		) == 0 ? null : contactDetails;
	}
}
