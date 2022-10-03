package c299.guessthenumber.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c299.guessthenumber.dao.DAO;
import c299.guessthenumber.dto.Game;

@Service
public class ServiceLayerImpl implements ServiceLayer {

	@Autowired
	private DAO dao;

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

	@Override
	public int createGame() {
		Game game = new Game();
		game.setAnswer(generateAnswer());
		return dao.addGame(game);
	}

	@Override
	public Game getGame(int id) {
		Game game = dao.getGameById(id);
		return game == null ? null : sanitizeGame(game);
	}

	@Override
	public List<Game> getAllGames() {
		List<Game> games = dao.getAllGames();
		for (Game game : games) game = sanitizeGame(game);
		return games;
	}
}
