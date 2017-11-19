package repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Admin;

public class RepositoryAdmin {
	private EntityManager manager;
	
	public RepositoryAdmin(EntityManager manager) {
		this.manager = manager;
	}
	
	public void insert(Admin admin) {
		manager.persist(admin);
	}
	
	public boolean validate(String email, String password) {
		Query query = manager.createQuery(
				"select c from Admin c where c.email = ?1 and c.password = ?2");
		query.setParameter(1, email).setParameter(2, password);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}
