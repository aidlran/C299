package c299.guessthenumber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import c299.guessthenumber.dto.Game;
import c299.guessthenumber.dto.Round;
import c299.guessthenumber.service.InvalidGuessException;
import c299.guessthenumber.service.ServiceLayer;
import c299.guessthenumber.service.UnexpectedBehaviourException;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class APIController {

	@Autowired
	private ServiceLayer service;

	/**
	 * POST /api/begin
	 * Creates a new game and return the game ID in the body.
	 * @return The new Game's ID.
	 */
	@PostMapping("begin")
	@ResponseStatus(HttpStatus.CREATED)
	public int createGame() {
		return service.createGame();
	}

	/**
	 * GET /api/game
	 * @return List of all existing Games.
	 */
	@GetMapping("game")
	public List<Game> getAllGames() {
		return service.getAllGames();
	}

	/**
	 * GET /api/game/{id}
	 * Gets an existing Game by its ID.
	 * The answer is removed if the game is still in progress.
	 * @param id A Game ID.
	 * @return
	 */
	@GetMapping("game/{id}")
	public Game getGame(@PathVariable int id) {
		Game game = service.getGame(id);
		if (game == null) throw new NotFoundException("Requested game does not exist.");
		return game;
	}

	/**
	 * POST /api/guess
	 * Handles a guess.
	 * @param guess {gameId: int, guess: int}
	 * @return The full Round object with all fields processed.
	 */
	@PostMapping("guess")
	public Round processGuess(@RequestBody Round guess) {
		try {
			return service.processGuess(guess);
		} catch (InvalidGuessException e) {
			throw new BadRequestException(e);
		} catch (UnexpectedBehaviourException e) {
			throw new ServerErrorException(e);
		}
	}
	
	/**
	 * GET /api/guess/{gameID}
	 * @param gameId The ID of a game.
	 * @return A list of all guesses for the game sorted from most recent to oldest.
	 */
	@GetMapping("guess/{gameId}")
	public List<Round> getRoundsForGame(@PathVariable int gameId) {
		return service.getRoundsForGame(getGame(gameId));
	}
}
