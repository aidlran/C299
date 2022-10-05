package c299.guessthenumber.dao;

import java.util.List;

import c299.guessthenumber.dto.Round;

public interface DAORound extends DAO<Round> {
    List<Round> getByGameId(int gameId);
}
