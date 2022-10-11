import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import c299.superherosightings.TestConfiguration;
import c299.superherosightings.dao.DAOCharacter;
import c299.superherosightings.dao.DAOLocation;
import c299.superherosightings.dao.DAOSighting;
import c299.superherosightings.dto.Location;
import c299.superherosightings.dto.Sighting;
import c299.superherosightings.dto.SuperCharacter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class DAOSightingTest {

	@Autowired
	private DAOSighting sightingDAO;

	@Autowired
	private DAOCharacter characterDAO;

	@Autowired
	private DAOLocation locationDAO;

	private SuperCharacter generateCharacter() {
		SuperCharacter character = new SuperCharacter();
		character.setType("hero");
		character.setName("Spider-Man");
		character.setDescription("Does whatever a spider can.");
		character.setSuperpower("Web-slinging");
		return character;
	}

	private Location generateLocation() {
		Location location = new Location();
		location.setName("New York City");
		location.setDescription("The Big Apple.");
		location.setLongitude(-73.9808);
		location.setLatitude(40.7648);
		return location;
	}

	@BeforeEach
	public void setUp() {
		for (Sighting sighting : sightingDAO.getAll())
			sightingDAO.removeById(sighting.getId());
		for (SuperCharacter character : characterDAO.getAll())
			characterDAO.removeById(character.getId());
		for (Location location : locationDAO.getAll())
			locationDAO.removeById(location.getId());
	}

	@Test
	public void testCRUD() {

		// Create Sighting
		Sighting sighting = new Sighting();
		sighting.setCharacterId(characterDAO.add(generateCharacter()).getId());
		sighting.setLocationId(locationDAO.add(generateLocation()).getId());
		sighting.setDescription("Northbound");
		sighting.setTimestamp(new Date(System.currentTimeMillis()));

		// Test INSERT and SELECT
		assertNotNull(sighting = sightingDAO.add(sighting));
		assertEquals(sighting, sightingDAO.getById(sighting.getId()));

		// Change something
		sighting.setDescription("Southbound");

		// Make sure .equals() works
		assertNotEquals(sighting, sightingDAO.getById(sighting.getId()));

		// Test UPDATE
		assertNotNull(sightingDAO.update(sighting));
		assertEquals(sighting, sightingDAO.getById(sighting.getId()));

		// Test DELETE
		assertTrue(sightingDAO.removeById(sighting.getId()));
		assertNull(sightingDAO.getById(sighting.getId()));
	}
}
