package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Supplier;

public class RepositorySupplier {

	private EntityManager manager;
	
	public RepositorySupplier(EntityManager manager) {
		this.manager = manager;
	}
	
	public void insert(Supplier supplier) {
		manager.persist(supplier);
	}
	
	public boolean validate(String email, String password) {
		Query query = manager.createQuery(
				"select c from Supplier c where c.email = ?1 and c.password = ?2");
		query.setParameter(1, email).setParameter(2, password);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public void remove(Supplier supplier) {
		this.manager.remove(supplier);
	}
	
	public Supplier findById(String email) {
		return this.manager.find(Supplier.class, email); 
	}
	
	public Supplier edit(Supplier supplier) {
		return this.manager.find(Supplier.class, supplier.getEmail()); 
	}
	
	public void update(Supplier supplier) {
		this.manager.merge(supplier);
	}
	
	public List<Supplier> getSuppliers() {
		Query query = this.manager.createQuery("select x from Supplier x");
		return query.getResultList();
	}
}
