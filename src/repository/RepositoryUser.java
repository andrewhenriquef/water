package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.User;

public class RepositoryUser {
	private EntityManager manager;
	
	public RepositoryUser(EntityManager manager) {
		this.manager = manager;
	}
	
	public void insert(User user) {
		manager.persist(user);
	}
	
	public boolean validate(String email, String password) {
		Query query = manager.createQuery(
				"select c from User c where c.email = ?1 and c.password = ?2");
		query.setParameter(1, email).setParameter(2, password);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	public void remove(User user) {
		this.manager.remove(user);
	}
	
	public User edit(User user) {
		return this.manager.find(User.class, user.getEmail()); 
	}
	
	public void update(User user) {
		this.manager.merge(user);
	}
	
	public List<User> getUsers() {
		Query query = this.manager.createQuery("select x from User x");
		return query.getResultList();
	}
	
}
