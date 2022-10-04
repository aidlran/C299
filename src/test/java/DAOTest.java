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
	DAO dao;

	private Game generateGame() {
		Game game = new Game();
		game.setAnswer(1234);
		return game;
	}

	@BeforeEach
	public void setUp() {
		for (Game game : dao.getAllGames()) {
			dao.removeGame(game.getId());
		}
	}

	@Test
	public void testAddGetGame() {
		Game game = dao.addGame(generateGame());
		assertEquals(
			game,
			dao.getGameById(game.getId())
		);
	}

	@Test
	public void testGetAllGames() {

		Game a = dao.addGame(generateGame());
		Game b = dao.addGame(generateGame());

		List<Game> all = dao.getAllGames();

		assertTrue(all.contains(a));
		assertTrue(all.contains(b));
		assertEquals(all.size(), 2);
	}

	@Test
	public void testUpdate() {

		Game test = dao.addGame(generateGame());
		Game fromDAO = dao.getGameById(test.getId());
		assertEquals(test, fromDAO);

		test.setFinished(true);
		dao.updateGame(test);
		assertNotEquals(test, fromDAO);

		fromDAO = dao.getGameById(test.getId());
		assertEquals(test, fromDAO);
	}

	@Test
	public void testDelete() {

		Game test = dao.addGame(generateGame());
		assertEquals(test, dao.getGameById(test.getId()));

		dao.removeGame(test.getId());
		assertNull(dao.getGameById(test.getId()));
	}
}
