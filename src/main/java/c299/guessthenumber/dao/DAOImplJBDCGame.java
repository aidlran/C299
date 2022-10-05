package c299.guessthenumber.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import c299.guessthenumber.dto.Game;

@Repository
public class DAOImplJBDCGame extends DAOImplJBDC<Game> {

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

	@Override
	protected String getTableName() {
		return "game";
	}

	@Override
	protected RowMapper<Game> getRowMapper() {
		return new GameMapper();
	}

	@Override
	@Transactional
	public Game add(Game game) {
		List<Integer> id = jdbcTemplate.query("INSERT INTO game (answer) VALUES (?) RETURNING id", new IdMapper(), game.getAnswer());
		if (id.size() == 0 || (game = getById(id.get(0))) == null) throw new DAOException();
		return game;
	}
}
