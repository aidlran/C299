package c299.guessthenumber.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import c299.guessthenumber.dao.DAO;
import c299.guessthenumber.dto.Game;
import c299.guessthenumber.dto.Round;

@Service
public class ServiceLayerImpl implements ServiceLayer {

	@Autowired
	private DAO<Game> gameDAO;

	@Autowired
	private DAO<Round> roundDAO;

	private static class UniqueDigitGenerator {

		private final Random RNG = new Random();
		private int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		private int availableDigits = 10;

		/**
		 * Get a random digit. Each subsequent call to this method in this instance will yield a unique digit,
		 * however an array out of bounds exception will be thrown if all ten digits have already been drawn.
		 * @return A random unique base-10 digit (0-9)
		 */
		public int getDigit() {

			// Select a digit from the array at a random index between 0 (inclusive) and availableDigits (exclusive)
			int randomIndex = RNG.nextInt(availableDigits);
			int randomDigit = digits[randomIndex];

			// The digit at the highest available index is copied to replace the digit at the index that was just selected
			// availableDigits is decremented, decreasing the pool of available digits
			digits[randomIndex] = digits[--availableDigits];

			return randomDigit;
		}
	}

	private int generateAnswer() {

		UniqueDigitGenerator generator = new UniqueDigitGenerator();

		int total = generator.getDigit();

		for (int exponent = 3; exponent > 0; exponent--) {
			total += Math.pow(10, exponent) * generator.getDigit();
		}

		return total;
	}

	private Game sanitizeGame(Game game) {
		if (!game.isFinished()) game.setAnswer(null);
		return game;
	}

	private int[] splitInt(int n, int length) {
		
		int[] digits = new int[length];

		for (int digit = length - 1; digit >= 0; digit--) {
			digits[digit] = n % 10;
			n /= 10;
		}

		return digits;
	}

	@Override
	public int createGame() {
		Game game = new Game();
		game.setAnswer(generateAnswer());
		return gameDAO.add(game).getId();
	}

	@Override
	public Game getGame(int id) {
		Game game = gameDAO.getById(id);
		return game == null ? null : sanitizeGame(game);
	}

	@Override
	public List<Game> getAllGames() {
		List<Game> games = gameDAO.getAll();
		for (Game game : games) game = sanitizeGame(game);
		return games;
	}

	@Transactional
	@Override
	public Round processGuess(Round guess) throws InvalidGuessException, UnexpectedBehaviourException {

		Game game;

		if (guess.getGameId() == null
		 || guess.getGuess() == null
		 || guess.getGuess() < 0
		 || guess.getGuess() > 9999
		 || (game = gameDAO.getById(guess.getGameId())) == null
		 || game.isFinished())
			throw new InvalidGuessException();

		System.out.println(game.isFinished());

		int[] answerDigits = splitInt(game.getAnswer(), 4);
		int[] guessDigits = splitInt(guess.getGuess(), 4);
		boolean[] exactMatch = new boolean[4];
		int exactMatches = 0;
		int partialMatches = 0;

		// Find indexes with exact matches
		for (int i = 0; i < 4; i++)
			if (answerDigits[i] == guessDigits[i]) {
				exactMatch[i] = true;
				exactMatches++;
			}

		if (exactMatches == 4) {
			game.setFinished(true);
			if (!gameDAO.update(game)) throw new UnexpectedBehaviourException();
		}
		
		// Find indexes with partial matches
		// First loop through non-exact matches
		else for (int i = 0; i < 4; i++) if (!exactMatch[i]) {
			// Then check other indexes to see if there is a match
			// Skipping self, or indexes with exact matches
			for (int j = 0; j < 4; j++) {
				if (j == i || exactMatch[j]) continue;
				else if (guessDigits[i] == answerDigits[j])
					partialMatches++;
			}
		}

		guess.setExactMatches(exactMatches);
		guess.setPartialMatches(partialMatches);

		return roundDAO.add(guess);
	}
}
