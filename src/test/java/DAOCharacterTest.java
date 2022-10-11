import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import c299.superherosightings.TestConfiguration;
import c299.superherosightings.dao.DAOCharacter;
import c299.superherosightings.dao.DAOOrganisation;
import c299.superherosightings.dto.Organisation;
import c299.superherosightings.dto.SuperCharacter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class DAOCharacterTest {

	@Autowired
	private DAOCharacter characterDAO;

	@Autowired
	private DAOOrganisation organisationDAO;

	private SuperCharacter generateHero() {
		SuperCharacter hero = new SuperCharacter();
		hero.setType("hero");
		hero.setName("Spiderman");
		hero.setDescription("Does whatever a spider can.");
		hero.setSuperpower("Web-slinging");
		return hero;
	}

	private SuperCharacter generateVillain() {
		SuperCharacter villain = new SuperCharacter();
		villain.setType("villain");
		villain.setName("Thanos");
		villain.setDescription("Purple boi");
		villain.setSuperpower("Infinity Gauntlet");
		return villain;
	}

	@BeforeEach
	public void setUp() {
		for (Organisation organisation : organisationDAO.getAll())
			organisationDAO.removeById(organisation.getId());
		for (SuperCharacter character : characterDAO.getAll())
			characterDAO.removeById(character.getId());
	}

	@Test
	public void testCRUD() {

		SuperCharacter hero, villain;

		// Test INSERT
		assertNotNull(hero = characterDAO.add(generateHero()));
		assertNotNull(villain = characterDAO.add(generateVillain()));

		// Test SELECT
		assertEquals(hero, characterDAO.getById(hero.getId()));
		assertEquals(villain, characterDAO.getById(villain.getId()));

		// Change something
		hero.setDescription("Nicknamed \"Spidey\". Wears a signature red and blue costume.");

		// Make sure .equals() works
		assertNotEquals(hero, characterDAO.getById(hero.getId()));

		// Test UPDATE
		assertNotNull(characterDAO.update(hero));
		assertEquals(hero, characterDAO.getById(hero.getId()));

		// Test DELETE
		assertTrue(characterDAO.removeById(hero.getId()));
		assertNull(characterDAO.getById(hero.getId()));
	}

	@Test
	public void testOrganisationMembership() {

		SuperCharacter hero = characterDAO.add(generateHero());
		SuperCharacter villain = characterDAO.add(generateVillain());

		Organisation organisation = new Organisation();
		organisation.setName("Ordinary Inc.");
		organisation = organisationDAO.add(organisation);

		organisationDAO.addMember(organisation.getId(), hero.getId());
		organisationDAO.addMember(organisation.getId(), villain.getId());

		List<SuperCharacter> members = characterDAO.getByOrganisation(organisation.getId());
		assertEquals(2, members.size());
		assertTrue(members.contains(hero));
		assertTrue(members.contains(villain));
	}
}
