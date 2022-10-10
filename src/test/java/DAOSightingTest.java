import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	public void testAddGet() {

		SuperCharacter character = new SuperCharacter();
		character.setType("hero");
		character.setName("Spiderman");
		character.setDescription("Does whatever a spider can.");
		character.setSuperpower("Web-slinging");
		character = characterDAO.add(character);

		Location location = new Location();
		location.setName("Test");
		location.setDescription("Test");
		location.setLongitude(1);
		location.setLatitude(2);
		location.setStreet("Test");
		location.setPostalCode("TE5 1TT");
		location = locationDAO.add(location);

		Sighting sighting = new Sighting();
		sighting.setCharacterId(character.getId());
		sighting.setLocationId(location.getId());
		sighting.setDescription("Test");
		sighting.setTimestamp(new Date(System.currentTimeMillis()));
		assertNotNull(sighting = sightingDAO.add(sighting));
		assertEquals(sighting, sightingDAO.getById(sighting.getId()));
	}
}
