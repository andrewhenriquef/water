package repository;

import javax.persistence.EntityManager;

import model.Reservoir;
import model.Supplier;

public class RepositoryReservoir {
	private EntityManager manager;
	
	public RepositoryReservoir(EntityManager manager) {
		this.manager = manager;
	}

	public Reservoir getReservoir() {	
		return this.manager.find(Reservoir.class, 1); 
	}

	public void update(Reservoir reservoir) {
		this.manager.merge(reservoir);
	}
	
}
