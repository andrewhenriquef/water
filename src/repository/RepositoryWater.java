package repository;

import javax.persistence.EntityManager;

import model.Supplier;
import model.Water;

public class RepositoryWater {

    private EntityManager manager;
	
	public RepositoryWater(EntityManager manager) {
		this.manager = manager;
	}
	
	public void insert(Water water) {
		manager.persist(water);
	}
	
	public Supplier findSupplier(String email) {
		return this.manager.find(Supplier.class, email); 
	}
}
