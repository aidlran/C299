package c299.guessthenumber.service;

import java.util.List;

import c299.guessthenumber.dto.Game;
import c299.guessthenumber.dto.Round;

public interface ServiceLayer {
    /**
     * Creates a new game with a randomly generated answer.
     * @return The ID of the new Game.
     */
    int createGame();
    Round processGuess(Round guess) throws InvalidGuessException, UnexpectedBehaviourException;
    Game getGame(int id);
    List<Game> getAllGames();
}
