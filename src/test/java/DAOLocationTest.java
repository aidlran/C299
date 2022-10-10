import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		Location location = new Location();
		location.setName("Test");
		location.setDescription("Test");
		location.setLongitude(1);
		location.setLatitude(2);
		location.setStreet("Test");
		location.setPostalCode("TE5 1TT");
		assertNotNull(location = dao.add(location));
		assertEquals(location, dao.getById(location.getId()));
	}
}
