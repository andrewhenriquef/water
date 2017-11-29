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
import repository.RepositorySupplier;

@ManagedBean
public class BeanSupplier {

	private String email;
	private String password;
	private String cnpj;
	private String name;
	private String phone;
	private String address;
	
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String auth() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		RepositorySupplier repository =	new RepositorySupplier(manager);
		if(repository.validate(email, password)) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("user", email);
			return "/home";
		} else {
			FacesMessage fm = new FacesMessage("login e/ou senha invalidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/supplier/supplier-login";
		}
	}
	
	public String register(){
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		RepositorySupplier repository = new RepositorySupplier(manager);
		
		Supplier supplier = new Supplier();
		
		supplier.setEmail(email);
		supplier.setPassword(password);
		supplier.setCnpj(cnpj);
		supplier.setName(name);
		supplier.setPhone(phone);
		supplier.setAddress(address);
		
		repository.insert(supplier);
		return "/supplier/supplier-login";
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

	public List<Supplier> getSuppliers() {
		EntityManager manager = this.getEntityManager();
		RepositorySupplier repository = new RepositorySupplier(manager);
		return repository.getSuppliers();
	}
	
	public String remove(Supplier supplier) {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		EntityManager manager = this.getEntityManager();
		RepositorySupplier repository = new RepositorySupplier(manager);
		repository.remove(supplier);
			
		FacesMessage fm = new FacesMessage("Fornecedor excluido com sucesso!");
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, fm);

		return "/supplier/index.xhtml";
	}
	
	public String edit(Supplier s) {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		EntityManager manager = this.getEntityManager();
		RepositorySupplier repository = new RepositorySupplier(manager);
		Supplier supplier = repository.edit(s);
		setEmail(supplier.getEmail());
		setPassword(supplier.getPassword());
		setCnpj(supplier.getCnpj());
		setName(supplier.getName());
		setPhone(supplier.getPhone());
		setAddress(supplier.getAddress());
	
		return "/supplier/supplier-edit.xhtml";
	}
	
	public String update() {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		EntityManager manager = this.getEntityManager();
		RepositorySupplier repository = new RepositorySupplier(manager);
		
		Supplier supplier = new Supplier();
		
		supplier.setEmail(email);
		supplier.setPassword(password);
		supplier.setCnpj(cnpj);
		supplier.setName(name);
		supplier.setPhone(phone);
		supplier.setAddress(address);
		
		repository.update(supplier);
			
		FacesMessage fm = new FacesMessage("Fornecedor editado com sucesso!");
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, fm);

		return "/supplier/index.xhtml";
	}
}
