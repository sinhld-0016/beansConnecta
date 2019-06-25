package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.UserDAO;
import vn.sun.entities.User;

@Repository
public class UserDAOImpl extends AbstractBaseDAO<Serializable, User> implements UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadEntities() {
		logger.info("load users");
		return getSession().createQuery("from Users").getResultList();
	}

	@Override
	public User findUserByEmail(String email) {
		User user = (User) getSession()
				.createQuery("from Users u where u.email = :email")
				.setParameter("email", email)
				.uniqueResult();
		return user;
	}

	@Override
	public User findById(int user_id) {
		User user_result = getSession().load(User.class, user_id, LockMode.OPTIMISTIC);
		if (user_result == null) {
			logger.error("Null object");
			return null;
		}
		else return user_result;
	}

	@Override
	public void createEntity(User user) {
		getSession().persist(user);
	}

}