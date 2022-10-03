package c299.guessthenumber.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import c299.guessthenumber.dto.Game;
import c299.guessthenumber.dto.Round;

@Repository
public class DAOImplJBDC implements DAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final class GameMapper implements RowMapper<Game> {
		@Override
		public Game mapRow(ResultSet resultSet, int index) throws SQLException {
			Game game = new Game();
			game.setId(resultSet.getInt("id"));
			game.setAnswer(resultSet.getInt("answer"));
			game.setFinished(resultSet.getBoolean("is_finished"));
			game.setStartTime(resultSet.getDate("start_time"));
			return game;
		}
	}

	private static final class RoundMapper implements RowMapper<Round> {
		@Override
		public Round mapRow(ResultSet resultSet, int index) throws SQLException {
			Round round = new Round();
			round.setId(resultSet.getInt("id"));
			round.setGuess(resultSet.getInt("guess"));
			round.setGuessTime(resultSet.getDate("guess_time"));
			round.setExactMatches(resultSet.getInt("exact_matches"));
			round.setPartialMatches(resultSet.getInt("partial_matches"));
			return round;
		}
	}

	private static final class IdMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet resultSet, int index) throws SQLException {
			return resultSet.getInt("id");
		}
	}

	@Override
	public Game getGameById(int id) {
		List<Game> result = jdbcTemplate.query("SELECT * FROM game WHERE id = ?", new GameMapper(), id);
		return result.size() == 0 ? null : result.get(0);
	}

	@Override
	public List<Game> getAllGames() {
		return jdbcTemplate.query("SELECT * FROM game", new GameMapper());
	}

	@Override
	public Integer addGame(Game game) {
		List<Integer> result = jdbcTemplate.query("INSERT INTO game (answer) VALUES (?) RETURNING id", new IdMapper(), game.getAnswer());
		return result.size() == 0 ? null : result.get(0);
	}

	@Override
	public boolean updateGame(Game game) {
		return jdbcTemplate.update("UPDATE game SET finished = ? WHERE id = ?", game.isFinished(), game.getId()) > 0;
	}

	@Override
	public boolean removeGame(int id) {
		return jdbcTemplate.update("DELETE FROM game WHERE id = ?", id) > 0;
	}
}
