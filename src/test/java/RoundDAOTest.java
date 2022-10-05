import static org.junit.jupiter.api.Assertions.assertEquals;
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

import c299.guessthenumber.TestConfiguration;
import c299.guessthenumber.dao.DAO;
import c299.guessthenumber.dto.Game;
import c299.guessthenumber.dto.Round;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
public class RoundDAOTest {

	@Autowired
	DAO<Game> gameDAO;

	@Autowired
	DAO<Round> roundDAO;

	Integer gameId;

	private Round generateRound(int guess, int exactMatches, int partialMatches) {
		Round round = new Round();
		round.setGuess(guess);
		round.setExactMatches(exactMatches);
		round.setPartialMatches(partialMatches);
		round.setGameId(gameId);
		return round;
	}

	private void generateGame() {
		Game game = new Game();
		game.setAnswer(1234);
		game = gameDAO.add(game);
		assertNotNull(game);
		gameId = game.getId();
	}

	@BeforeEach
	public void setUp() {

		if (gameId == null) generateGame();

		for (Round round : roundDAO.getAll()) {
			roundDAO.removeById(round.getId());
		}
	}

	@Test
	public void testAddGet() {
		Round round = roundDAO.add(generateRound(1233, 3, 0));
		assertEquals(
			round,
			roundDAO.getById(round.getId())
		);
	}

	@Test
	public void testGetAll() {

		Round a = roundDAO.add(generateRound(1233, 3, 0));
		Round b = roundDAO.add(generateRound(4321, 0, 4));

		List<Round> all = roundDAO.getAll();

		assertTrue(all.contains(a));
		assertTrue(all.contains(b));
		assertEquals(all.size(), 2);
	}

	@Test
	public void testDelete() {

		Round test = roundDAO.add(generateRound(1233, 3, 0));
		assertEquals(test, roundDAO.getById(test.getId()));

		roundDAO.removeById(test.getId());
		assertNull(roundDAO.getById(test.getId()));
	}
}
