import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import c299.superherosightings.TestConfiguration;
import c299.superherosightings.dao.DAOContactDetails;
import c299.superherosightings.dto.ContactDetails;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class DAOContactDetailsTest {

	@Autowired
	private DAOContactDetails dao;

	@BeforeEach
	public void setUp() {
		for (ContactDetails contactDetails : dao.getAll())
			dao.removeById(contactDetails.getId());
	}

	@Test
	public void testCRUD() {

		// Create object
		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setEmailAddress("test@example.com");
		contactDetails.setPhoneNumber("+555 1234");

		// Test INSERT and SELECT
		assertNotNull(contactDetails = dao.add(contactDetails));
		assertEquals(contactDetails, dao.getById(contactDetails.getId()));

		// Change something
		contactDetails.setNote("Ask for \"X\"");

		// Make sure .equals() works
		assertNotEquals(contactDetails, dao.getById(contactDetails.getId()));

		// Test UPDATE
		assertNotNull(dao.update(contactDetails));
		assertEquals(contactDetails, dao.getById(contactDetails.getId()));

		// Test DELETE
		assertTrue(dao.removeById(contactDetails.getId()));
		assertNull(dao.getById(contactDetails.getId()));
	}
}
