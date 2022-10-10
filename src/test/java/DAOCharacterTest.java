import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import c299.superherosightings.TestConfiguration;
import c299.superherosightings.dao.DAOCharacter;
import c299.superherosightings.dto.SuperCharacter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class DAOCharacterTest {

	@Autowired
	private DAOCharacter dao;

	@BeforeEach
	public void setUp() {
		for (SuperCharacter character : dao.getAll())
			dao.removeById(character.getId());
	}

	@Test
	public void testAddGet() {

		SuperCharacter hero = new SuperCharacter();
		hero.setType("hero");
		hero.setName("Spiderman");
		hero.setDescription("Does whatever a spider can.");
		hero.setSuperpower("Web-slinging");

		SuperCharacter villain = new SuperCharacter();
		villain.setType("villain");
		villain.setName("Thanos");
		villain.setDescription("Purple boi");
		villain.setSuperpower("Infinity Gauntlet");

		assertNotNull(hero = dao.add(hero));
		assertNotNull(villain = dao.add(villain));

		assertEquals(hero, dao.getById(hero.getId()));
		assertEquals(villain, dao.getById(villain.getId()));
	}
}
