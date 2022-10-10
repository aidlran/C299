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
    public ContactDetails add(ContactDetails object) {
		List<Integer> id = jdbcTemplate.query(
				"INSERT INTO " + getTableName() + " (location_id, phone_number, email_address, note) " +
				"VALUES (?, ?, ?, ?) " +
				"RETURNING id",
				new IdMapper(),
				object.getLocationId(),
				object.getPhoneNumber(),
                object.getEmailAddress(),
                object.getNote()
		);
		return (id.size() == 0 || (object = getById(id.get(0))) == null) ? null : object;
    }    
}
