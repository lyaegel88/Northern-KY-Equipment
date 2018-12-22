package com.nke.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nke.domain.User;
import com.nke.domain.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum)
		throws SQLException {
			User user = new User();
			user.setUserId(rs.getInt("id"));
			user.setCreatedAt(rs.getString("created_at"));
			user.setUpdatedAt(rs.getString("updated_at"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmailAddress(rs.getString("email"));
			user.setUsername(rs.getString("username"));
			user.setUserRole(rs.getString("role"));
			user.setCurrentPassword(rs.getString("password"));
			user.setUserEnabled(rs.getInt("enabled"));
			user.setUserToken(rs.getString("token"));
			
			return user;
		}
	}
	
@Override
	public List<User> getUserByUsername(String username){
		String SQL = "SELECT * FROM user WHERE username = :username";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		List<User> result = jdbcTemplate.query(SQL, params, new UserMapper());
		
		
		return result;
	}
@Override
	public void updateUserEmail(String newUpdatedDate, String emailAddress, int userId) {
		String SQL = "UPDATE user SET updated_at = :updated_at, email = :email WHERE id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("updated_at", newUpdatedDate);
		params.put("email", emailAddress);
		params.put("id", userId);
		
		jdbcTemplate.update(SQL, params);
}

@Override
public void updateUserPassword(String newUpdatedDate, String newPassword, int userId) {
	String SQL = "UPDATE user SET updated_at = :updated_at, password = :password WHERE id = :id";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("updated_at", newUpdatedDate);
	params.put("password", newPassword);
	params.put("id", userId);
	
	jdbcTemplate.update(SQL, params);
}

}
