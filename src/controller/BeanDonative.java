package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Donative;
import model.Reservoir;
import model.User;
import repository.RepositoryDonative;
import repository.RepositoryReservoir;
import repository.RepositoryUser;

@ManagedBean
public class BeanDonative {
	
	private int littersRequested;
	private User user;
	private Reservoir reservoir;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Reservoir getReservoir() {
		return reservoir;
	}
	public void setReservoir(Reservoir reservoir) {
		this.reservoir = reservoir;
	}
	public int getLittersRequested() {
		return littersRequested;
	}
	public void setLittersRequested(int littersRequested) {
		this.littersRequested = littersRequested;
	}
	

	public String requestDonation() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String userEmail = (String)session.getAttribute("user");
		RepositoryUser repositoryUser = new RepositoryUser(manager);
		User user = repositoryUser.findById(userEmail);
		
		RepositoryReservoir repositoryReservoir = new RepositoryReservoir(manager);
		Reservoir reservoir = repositoryReservoir.getReservoir();
		
		//update reservoir
		reservoir.setLitters(reservoir.getLitters() - littersRequested);
		repositoryReservoir.update(reservoir);
		
		Donative donative = new Donative();
		
		donative.setLitters_donated(littersRequested);
		donative.setUser(user);
		donative.setReservoir(reservoir);
		
		RepositoryDonative repositoryDonative = new RepositoryDonative(manager);
		repositoryDonative.insert(donative);
		
		return "/user/user-get-donation.xhtml";
	}
	
	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager)request.getAttribute("EntityManager");
		return manager;
	}
	
	
}
