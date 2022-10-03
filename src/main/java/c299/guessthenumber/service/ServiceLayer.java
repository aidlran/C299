package c299.guessthenumber.service;

import java.util.List;

import c299.guessthenumber.dto.Game;

public interface ServiceLayer {
    /**
     * Creates a new game with a randomly generated answer.
     * @return The ID of the new Game.
     */
    int createGame();
    Game getGame(int id);
    List<Game> getAllGames();
}
