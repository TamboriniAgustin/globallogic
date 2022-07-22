package global.logic.bci.test.utils.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import global.logic.bci.test.models.User;
import global.logic.bci.test.models.User.UserBuilder;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserBuilder user = User.builder();
		
		user.id(rs.getString("user_id"));
		user.name(rs.getString("user_name"));
		user.email(rs.getString("user_email"));
		user.password(rs.getString("user_password"));
		user.created(rs.getTimestamp("user_created"));
		user.lastLogin(rs.getTimestamp("user_last_login"));
		user.token(rs.getString("user_token"));
		user.isActive(rs.getBoolean("user_is_active"));
		
		return user.build();
	}
}