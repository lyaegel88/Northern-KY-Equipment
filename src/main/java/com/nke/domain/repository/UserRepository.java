package com.nke.domain.repository;

import java.util.List;

import com.nke.domain.User;

public interface UserRepository {

	List<User> getUserByUsername(String username);

	void updateUserEmail(String newUpdatedDate, String emailAddress, int userId);

	void updateUserPassword(String newUpdatedDate, String newPassword, int userId);

}
