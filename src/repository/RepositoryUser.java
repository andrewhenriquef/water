package repository;

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
}
