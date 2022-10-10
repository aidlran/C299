import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		Organisation organisation = new Organisation();
		organisation.setName("Test");
		organisation.setDescription("Test");
		assertNotNull(organisation = dao.add(organisation));
		assertEquals(organisation, dao.getById(organisation.getId()));
	}
}
