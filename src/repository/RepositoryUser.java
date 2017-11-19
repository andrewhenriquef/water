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
	
	public void inserir(User user) {
		manager.persist(user);
	}
	
	public boolean validar(String login, String password) {
		Query query = manager.createQuery(
				"select c from User c where c.login = ?1 and c.password = ?2");
		query.setParameter(1, login).setParameter(2, password);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}
