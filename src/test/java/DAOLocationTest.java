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
import c299.superherosightings.dao.DAOLocation;
import c299.superherosightings.dto.Location;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class DAOLocationTest {

	@Autowired
	private DAOLocation dao;

	@BeforeEach
	public void setUp() {
		for (Location location : dao.getAll())
			dao.removeById(location.getId());
	}

	@Test
	public void testAddGet() {

		// Create object
		Location location = new Location();
		location.setName("New York City");
		location.setDescription("The Big Apple.");
		location.setLongitude(-73.9808);
		location.setLatitude(40.7648);

		// Test INSERT and SELECT
		assertNotNull(location = dao.add(location));
		assertEquals(location, dao.getById(location.getId()));

		// Change something
		location.setDescription("The city that never sleeps.");

		// Make sure .equals() works
		assertNotEquals(location, dao.getById(location.getId()));

		// Test UPDATE
		assertNotNull(dao.update(location));
		assertEquals(location, dao.getById(location.getId()));

		// Test DELETE
		assertTrue(dao.removeById(location.getId()));
		assertNull(dao.getById(location.getId()));
	}
}
