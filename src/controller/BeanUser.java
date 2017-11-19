package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;
import repository.RepositoryUser;

@ManagedBean
public class BeanUser {

	private String password;
	private String cpf;
	private String rg;
	private String name;
	private Integer members_number;
	private String email;
	private String phone;
	private String address;

	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMembers_number() {
		return members_number;
	}
	public void setMembers_number(Integer members_number) {
		this.members_number = members_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
		RepositoryUser repository =	new RepositoryUser(manager);
		if(repository.validate(email, password)) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("user", email);
			return "/home";
		} else {
			FacesMessage fm = new FacesMessage("login e/ou senha invalidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/user/user-login";
		}
	}
	
	public String register(){
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		RepositoryUser repository = new RepositoryUser(manager);
		
		User user = new User();
		
		user.setEmail(email);
		user.setPassword(password);
		user.setCpf(cpf);
		user.setRg(rg);
		user.setMembers_number(members_number);
		user.setName(name);
		user.setPhone(phone);
		user.setAddress(address);
		
		repository.insert(user);
		return "/user/user-login";
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
