package c299.guessthenumber.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import c299.guessthenumber.dto.Round;

@Repository
public class DAOImplJBDCRound extends DAOImplJBDC<Round> implements DAORound {

	private static final class RoundMapper implements RowMapper<Round> {
		@Override
		public Round mapRow(ResultSet resultSet, int index) throws SQLException {
			Round round = new Round();
			round.setId(resultSet.getInt("id"));
			round.setGuess(resultSet.getInt("guess"));
			round.setGuessTime(resultSet.getDate("guess_time"));
			round.setExactMatches(resultSet.getInt("exact_matches"));
			round.setPartialMatches(resultSet.getInt("partial_matches"));
			round.setGameId(resultSet.getInt("game_id"));
			return round;
		}
	}

	@Override
	protected String getTableName() {
		return "round";
	}

	@Override
	protected RowMapper<Round> getRowMapper() {
		return new RoundMapper();
	}

	@Override
	@Transactional
	public Round add(Round round) {
		List<Integer> id = jdbcTemplate.query("INSERT INTO round (guess, exact_matches, partial_matches, game_id) VALUES (?, ?, ?, ?) RETURNING id", new IdMapper(), round.getGuess(), round.getExactMatches(), round.getPartialMatches(), round.getGameId());
		if (id.size() == 0 || (round = getById(id.get(0))) == null) throw new DAOException();
		return round;
	}

	@Override
	public List<Round> getByGameId(int id) {
		return jdbcTemplate.query("SELECT r.* FROM round r JOIN game g ON g.id = r.game_id WHERE g.id = ? ORDER BY guess_time DESC", new RoundMapper(), id);
	}
}
