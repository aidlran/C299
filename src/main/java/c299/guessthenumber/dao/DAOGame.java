package c299.guessthenumber.dao;

import c299.guessthenumber.dto.Game;

public interface DAOGame extends DAO<Game> {
    boolean setFinished(int id);
}
