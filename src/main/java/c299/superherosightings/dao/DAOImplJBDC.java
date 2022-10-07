package c299.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

abstract class DAOImplJBDC<T> implements DAO<T> {

	protected JdbcTemplate jdbcTemplate;

	protected abstract String getTableName();
	protected abstract RowMapper<T> getRowMapper();

	protected static final class IdMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet resultSet, int index) throws SQLException {
			return resultSet.getInt("id");
		}
	}

	@Autowired
	public DAOImplJBDC(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public T getById(int id) {
		List<T> result = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE id = ?", getRowMapper(), id);
		return result.size() == 0 ? null : result.get(0);
	}

	@Override
	public List<T> getAll() {
		return jdbcTemplate.query("SELECT * FROM " + getTableName(), getRowMapper());
	}

	@Override
	public boolean removeById(int id) {
		return jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", id) > 0;
	}
}
