package repository;

import javax.persistence.EntityManager;

import model.Donative;

public class RepositoryDonative {
	private EntityManager manager;
	
	public RepositoryDonative(EntityManager manager) {
		this.manager = manager;
	}

	public void insert(Donative donative) {
		this.manager.persist(donative);
	}
}
