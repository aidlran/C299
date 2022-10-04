package c299.guessthenumber.dao;

import java.util.List;

import c299.guessthenumber.dto.Game;

public interface DAO {
    Game getGameById(int id);
    List<Game> getAllGames();
    Game addGame(Game name);
    boolean updateGame(Game test);
    boolean removeGame(int id);
}
