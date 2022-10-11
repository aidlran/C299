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
import c299.superherosightings.dao.DAOOrganisation;
import c299.superherosightings.dto.Organisation;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class DAOOrganisationTest {

	@Autowired
	private DAOOrganisation dao;

	@BeforeEach
	public void setUp() {
		for (Organisation organisation : dao.getAll())
			dao.removeById(organisation.getId());
	}

	@Test
	public void testAddGet() {

		// Create object
		Organisation organisation = new Organisation();
		organisation.setName("Ordinary Inc.");

		// Test INSERT and SELECT
		assertNotNull(organisation = dao.add(organisation));
		assertEquals(organisation, dao.getById(organisation.getId()));

		// Change something
		organisation.setDescription("A new collective of supers.");

		// Make sure .equals() works
		assertNotEquals(organisation, dao.getById(organisation.getId()));

		// Test UPDATE
		assertNotNull(dao.update(organisation));
		assertEquals(organisation, dao.getById(organisation.getId()));

		// Test DELETE
		assertTrue(dao.removeById(organisation.getId()));
		assertNull(dao.getById(organisation.getId()));
	}
}
