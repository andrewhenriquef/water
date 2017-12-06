package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Reservoir;
import model.Supplier;
import model.Water;
import repository.RepositoryReservoir;
import repository.RepositoryWater;

@ManagedBean
public class BeanWater {
	private int id;
	private int total_litters;
	private int used_litters;
	private Supplier supplier;
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal_litters() {
		return total_litters;
	}
	public void setTotal_litters(int total_litters) {
		this.total_litters = total_litters;
	}
	public int getUsed_litters() {
		return used_litters;
	}
	public void setUsed_litters(int used_litters) {
		this.used_litters = used_litters;
	}
	
	public String donate() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		RepositoryWater repository = new RepositoryWater(manager);
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String userEmail = (String)session.getAttribute("user");
		Supplier supplier = repository.findSupplier(userEmail);
		RepositoryReservoir repositoryReservoir = new RepositoryReservoir(manager);
		Reservoir reservoir = repositoryReservoir.getReservoir();
		
		Water water = new Water();
		water.setTotal_litters(total_litters);
		water.setSupplier(supplier);

		reservoir.setLitters(reservoir.getLitters() + total_litters);
		repositoryReservoir.update(reservoir);
		repository.insert(water);
		
		return "/supplier/index.xhtml";
	}
	
	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager)request.getAttribute("EntityManager");
		return manager;
	}

}
