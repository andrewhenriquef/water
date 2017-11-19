package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Admin;
import repository.RepositoryAdmin;

@ManagedBean
public class BeanAdmin {
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String auth() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		RepositoryAdmin repository =	new RepositoryAdmin(manager);
		if(repository.validate(email, password)) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("user", email);
			return "/home";
		} else {
			FacesMessage fm = new FacesMessage("login e/ou senha invalidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/admin/admin-login";
		}
	}
	
	public String register(){
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		RepositoryAdmin repository = new RepositoryAdmin(manager);
		
		Admin admin = new Admin();
		
		admin.setEmail(email);
		admin.setPassword(password);
		
		repository.insert(admin);
		return "/home";
	}
	
	public String loggout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("user");
		session.invalidate();
		return "/index.xhtml";
	}
	
	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager)request.getAttribute("EntityManager");
		return manager;
	}


}
