package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String email;
	private String password;
	private String cpf;
	private String rg;
	private String name;
	private Integer members_number;
	private String phone;
	private String address;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

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
	
}
	