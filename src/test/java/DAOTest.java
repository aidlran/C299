import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import c299.guessthenumber.TestConfiguration;
import c299.guessthenumber.dao.DAO;
import c299.guessthenumber.dto.Game;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class DAOTest {

	@Autowired
	DAO<Game> dao;

	private Game generateGame() {
		Game game = new Game();
		game.setAnswer(1234);
		return game;
	}

	@BeforeEach
	public void setUp() {
		for (Game game : dao.getAll()) {
			dao.removeById(game.getId());
		}
	}

	@Test
	public void testAddGetGame() {
		Game game = dao.add(generateGame());
		assertEquals(
			game,
			dao.getById(game.getId())
		);
	}

	@Test
	public void testGetAllGames() {

		Game a = dao.add(generateGame());
		Game b = dao.add(generateGame());

		List<Game> all = dao.getAll();

		assertTrue(all.contains(a));
		assertTrue(all.contains(b));
		assertEquals(all.size(), 2);
	}

	@Test
	public void testUpdate() {

		Game test = dao.add(generateGame());
		Game fromDAO = dao.getById(test.getId());
		assertEquals(test, fromDAO);

		test.setFinished(true);
		dao.update(test);
		assertNotEquals(test, fromDAO);

		fromDAO = dao.getById(test.getId());
		assertEquals(test, fromDAO);
	}

	@Test
	public void testDelete() {

		Game test = dao.add(generateGame());
		assertEquals(test, dao.getById(test.getId()));

		dao.removeById(test.getId());
		assertNull(dao.getById(test.getId()));
	}
}
