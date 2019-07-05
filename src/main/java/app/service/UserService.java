package app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import app.model.User;

public interface UserService extends BaseService<Integer, User> {
	
	User findByEmailAndPassword(String usermail, String password);
	
	boolean createUser(User user);
	
	Page<User> loadUsers(String userName, Pageable pageable);
	
	int userCount(String userName);

	boolean deleteUser(Integer id);
	
	boolean isEmailExist(String email);
	
	boolean updateUser(User userView, User user);
	
}
