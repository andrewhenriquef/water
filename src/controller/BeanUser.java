package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Supplier;
import model.User;
import repository.RepositorySupplier;
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
	private int littersRequested;
	
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
	
	public List<User> getUsers() {
		EntityManager manager = this.getEntityManager();
		RepositoryUser repository = new RepositoryUser(manager);
		return repository.getUsers();
	}

	public String remove(User user) {
		FacesContext fc = FacesContext.getCurrentInstance();

		EntityManager manager = this.getEntityManager();
		RepositoryUser repository = new RepositoryUser(manager);
		repository.remove(user);

		FacesMessage fm = new FacesMessage("Usuário excluido com sucesso!");
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, fm);

		return "/user/user-index.xhtml";
	}
	
	public String show(User u) {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		EntityManager manager = this.getEntityManager();
		RepositoryUser repository = new RepositoryUser(manager);
		User user = repository.edit(u);

		setEmail(user.getEmail());
		setPassword(user.getPassword());
		setRg(user.getRg());
		setCpf(user.getCpf());
		setName(user.getName());
		setMembers_number(user.getMembers_number());
		setPhone(user.getPhone());
		setAddress(user.getAddress());
	
		return "/user/user-profile.xhtml";
	}
	
	public String showById(String email) {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		EntityManager manager = this.getEntityManager();
		RepositoryUser repository = new RepositoryUser(manager);
		User user = repository.findById(email);
		
		setEmail(user.getEmail());
		setPassword(user.getPassword());
		setRg(user.getRg());
		setCpf(user.getCpf());
		setName(user.getName());
		setMembers_number(user.getMembers_number());
		setPhone(user.getPhone());
		setAddress(user.getAddress());		
	
		return "/user/user-profile.xhtml";
	}

	public String edit(User u) {
		FacesContext fc = FacesContext.getCurrentInstance();

		EntityManager manager = this.getEntityManager();
		RepositoryUser repository = new RepositoryUser(manager);
		User user = repository.edit(u);
		setEmail(user.getEmail());
		setPassword(user.getPassword());
		setMembers_number(user.getMembers_number());
		setCpf(user.getCpf());
		setName(user.getName());
		setPhone(user.getPhone());
		setAddress(user.getAddress());

		return "/user/user-edit.xhtml";
	}

	public String update() {
		FacesContext fc = FacesContext.getCurrentInstance();

		EntityManager manager = this.getEntityManager();
		RepositoryUser repository = new RepositoryUser(manager);

		User user = new User();

		user.setEmail(email);
		user.setPassword(password);
		user.setCpf(cpf);
		user.setMembers_number(members_number);
		user.setName(name);
		user.setPhone(phone);
		user.setAddress(address);

		repository.update(user);

		FacesMessage fm = new FacesMessage("Usuario editado com sucesso!");
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, fm);

		return "/user/user-index.xhtml";
	}

}
