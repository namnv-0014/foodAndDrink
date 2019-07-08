package app.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.dao.GenericDAO;
import app.dao.UserDAO;
import app.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User findUserByEmail(String email) {
		return (User) getSession().createQuery("from User where email=:email")
				.setParameter("email", email).uniqueResult();
	}

	@Override
	public boolean checkEmail(String email) {
		User user = (User) getSession().createQuery("from User where email = :userEmail")
				.setParameter("userEmail", email).uniqueResult();
		return user != null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<User> loadUsers(String userName, int size, int page) {
		return (List<User>) getSession().createQuery("from User where name like :userName")
				.setString("userName", "%"+userName+"%")
				.setFirstResult(page*size)
				.setMaxResults(size)
				.getResultList();
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public int userCount(String userName) {
		return Integer.parseInt(getSession().createQuery("SELECT COUNT(*) FROM User where name like :userName")
				.setString("userName", "%"+userName+"%")
				.getSingleResult().toString());
	}
	
}
